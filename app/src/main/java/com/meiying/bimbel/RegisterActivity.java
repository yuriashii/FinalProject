package com.meiying.bimbel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.meiying.bimbel.config.FireUrl;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private ProgressDialog pd;
    EditText email,nama,repassword,password,kode;
    private Snackbar snackbar;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        nama = findViewById(R.id.nama);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        kode= findViewById(R.id.kode_bimbel);
        firebaseAuth =firebaseAuth.getInstance();
        myRef = database.getReference("users");
        pd = new ProgressDialog(RegisterActivity.this);
        Button register = findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(kode.getText().toString().equals("")){
                    kode.setError("Harap isi ID Bimbel");
                    kode.requestFocus();
                }else if(nama.getText().toString().equals("")){
                    nama.setError("Harap isi nama anda");
                    nama.requestFocus();
                }else if(email.getText().toString().equals("")){
                    email.setError("Harap isi email anda");
                    email.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
                {
                    email.setError("Masukkan email yang valid");
                    email.requestFocus();
                    return;
                }
                else if(password.getText().toString().length()<6)
                {

                    password.setError("Panjang password harus lebih dari 6");
                    password.requestFocus();
                    return;
                }
                else if(password.getText().toString().equals("")){
                    password.setError("Harap isi password");
                    password.requestFocus();
                }else if(repassword.getText().toString().equals("")){
                    repassword.setError("Harap ulangi password");
                    repassword.requestFocus();
                }else if(!password.getText().toString().equals(repassword.getText().toString())){
                    repassword.setError("Password Tidak Sama");
                    repassword.requestFocus();
                }else{
                    register();
                }
            }
        });

        final int[] show = {0};
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (password.getRight() - password.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (show[0] ==0){
                            password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            show[0] = 1;
                        }else{
                            password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            show[0] = 0;
                        }
                        return true;
                    }
                }
                return false;
            }
        });

        final int[] reshow = {0};
        repassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (repassword.getRight() - repassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        if (reshow[0] ==0){
                            repassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                            repassword.setInputType(InputType.TYPE_CLASS_TEXT);
                            reshow[0] = 1;
                        }else{
                            repassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                            repassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            reshow[0] = 0;
                        }
                        return true;
                    }
                }
                return false;
            }
        });


    }

    private void register(){

        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.e("task",task.toString());
                        if(task.isSuccessful())
                        {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            showSnackbar("Berhasil Mendaftar");
//                            HashMap<String,String> data = new HashMap<>();
//                            data.put("nama",email.getText().toString();
//                            data.put("email",email.getText().toString());
//                            data.put("idbimbel",kode.getText().toString());
//                            data.put("password",password.getText().toString());
//                            JSONObject data_login = new JSONObject(data);
                            UserModel data_login = new UserModel(user.getUid(),kode.getText().toString()
                                    ,nama.getText().toString()
                                    ,email.getText().toString()
                                    ,password.getText().toString()
                            );
                            myRef.child(user.getUid()).setValue(data_login);
                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setTitle("Berhasil")
                                    .setMessage("Berhasil register, lakukan login sekarang ?")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                            intent.putExtra("email",email.getText().toString());
                                            startActivity(intent);
                                            finish();
                                        }
                                    }).show();
                        }
                        else
                        {
                          showSnackbar("Gagal! Email Telah Terdaftar ");
                        }
                    }
        });

    }

    public void showSnackbar(String stringSnackbar){
        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.thernary))
                .show();

    }
}