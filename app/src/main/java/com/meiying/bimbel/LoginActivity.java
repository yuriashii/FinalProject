package com.meiying.bimbel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.ProgressDialog;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    private ProgressDialog pd;
    EditText email, password;
    private Snackbar snackbar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        firebaseAuth =firebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null){
            Log.e("user",user.toString());
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        TextView register = findViewById(R.id.register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        pd = new ProgressDialog(LoginActivity.this);

        Button login = findViewById(R.id.login);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(email.getText().toString().length()==0)
                {
                    email.setError("Harap isi email anda");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
                {
                    email.setError("Masukkan email yang valid");
                    email.requestFocus();
                    return;
                }
                if(password.getText().toString().length()==0)
                {

                    password.setError("Harap isi password");
                    password.requestFocus();
                    return;
                }
                if(password.getText().toString().length()<6)
                {
                   password.setError("Panjang password harus lebih dari 6");
                    password.requestFocus();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                    {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                    else
                    {
                        showSnackbar("Gagal, Pastikan akun login sesuai");
                    }

                });


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


    }

    @Override
    protected void onStart() {
        super.onStart();

//        if(currentUser != null){
//            reload();
//        }
    }

    public void showSnackbar(String stringSnackbar){
        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.thernary))
                .show();

    }
}