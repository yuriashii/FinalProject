package com.meiying.bimbel.ui.area;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.meiying.bimbel.databinding.FragmentAreaBinding;

public class AreaFragment extends Fragment {

    private FragmentAreaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentAreaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.hitungPersegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.panjang.getText().toString().length()==0){
                    binding.panjang.setError("isi");
                    binding.panjang.requestFocus();
                }else if(binding.lebar.getText().toString().length()==0){
                    binding.lebar.setError("isi");
                    binding.lebar.requestFocus();
                }else{
                    Double nilai_panjang = Double.parseDouble(binding.panjang.getText().toString());
                    Double nilai_lebar = Double.parseDouble(binding.lebar.getText().toString());
                    Double luas = nilai_panjang*nilai_lebar;
                    binding.hasilPersegi.setText("Hasil : "+String.valueOf(luas));
                }
            }
        });


        binding.hitungSegitiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.alas.getText().toString().length()==0){
                    binding.alas.setError("isi");
                    binding.alas.requestFocus();
                }else if(binding.tinggi.getText().toString().length()==0){
                    binding.tinggi.setError("isi");
                    binding.tinggi.requestFocus();
                }else{
                    Double nilai_alas = Double.parseDouble(binding.alas.getText().toString());
                    Double nilai_tinggi = Double.parseDouble(binding.tinggi.getText().toString());
                    Double luas = (nilai_alas*nilai_tinggi)/2;
                    binding.hasilSegitiga.setText("Hasil : "+String.valueOf(luas));
                }
            }
        });

        binding.hitungLingkaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(binding.ruas.getText().toString().length()==0){
                    binding.ruas.setError("isi");
                    binding.ruas.requestFocus();
                }else{
                     Double nilai_ruas = Double.parseDouble(binding.ruas.getText().toString());
                     if (nilai_ruas % 7==0){
                        Double luas = (22*nilai_ruas*nilai_ruas)/7;
                        binding.hasilLingkaran.setText("Hasil : "+String.valueOf(luas));
                    }else{
                        Double luas = 3.14*nilai_ruas*nilai_ruas;
                        binding.hasilLingkaran.setText("Hasil : "+String.valueOf(luas));
                    }
                }
            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.panjang.setText("");
        binding.lebar.setText("");
        binding.alas.setText("");
        binding.tinggi.setText("");
        binding.ruas.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}