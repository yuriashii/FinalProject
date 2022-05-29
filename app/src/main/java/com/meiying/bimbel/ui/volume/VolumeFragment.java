package com.meiying.bimbel.ui.volume;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.meiying.bimbel.databinding.FragmentVolumeBinding;

public class VolumeFragment extends Fragment {

    private FragmentVolumeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         binding = FragmentVolumeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.hitungBalok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.panjangBalok.getText().toString().length()==0){
                    binding.panjangBalok.requestFocus();
                }else if(binding.lebarBalok.getText().toString().length()==0){
                    binding.lebarBalok.requestFocus();
                }else if(binding.tinggiBalok.getText().toString().length()==0){
                    binding.tinggiBalok.requestFocus();
                }else{
                    Double nilai_panjang = Double.parseDouble(binding.panjangBalok.getText().toString());
                    Double nilai_lebar = Double.parseDouble(binding.lebarBalok.getText().toString());
                    Double nilai_tinggi = Double.parseDouble(binding.tinggiBalok.getText().toString());
                    Double luas = nilai_panjang*nilai_lebar*nilai_tinggi;
                    binding.hasilBalok.setText("Hasil : "+String.valueOf(luas));
                }
            }
        });


        binding.hitungPiramid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.panjangPiramid.getText().toString().length()==0){
                    binding.panjangPiramid.requestFocus();
                }else if(binding.lebarPiramid.getText().toString().length()==0){
                    binding.lebarPiramid.requestFocus();
                }else if(binding.tinggiPiramid.getText().toString().length()==0){
                    binding.tinggiPiramid.requestFocus();
                }else{
                    Double nilai_panjang = Double.parseDouble(binding.panjangPiramid.getText().toString());
                    Double nilai_lebar = Double.parseDouble(binding.lebarPiramid.getText().toString());
                    Double nilai_tinggi = Double.parseDouble(binding.tinggiPiramid.getText().toString());
                    Double luas = ((nilai_panjang*nilai_lebar)*nilai_tinggi)/3;
                    binding.hasilPiramid.setText("Hasil : "+String.valueOf(luas));
                }
            }
        });

        binding.hitungTabung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.ruas.getText().toString().length()==0){
                    binding.ruas.requestFocus();
                }else if(binding.tinggiTabung.getText().toString().length()==0){
                    binding.tinggiTabung.requestFocus();
                }else{
                    Double nilai_ruas = Double.parseDouble(binding.ruas.getText().toString());
                    Double nilai_tinggi = Double.parseDouble(binding.tinggiTabung.getText().toString());
                    if (nilai_ruas % 7==0){
                        Double luas = (22*nilai_ruas*nilai_ruas)/7*nilai_tinggi;
                        binding.hasilTabung.setText("Hasil : "+String.valueOf(luas));
                    }else{
                        Double luas = 3.14*nilai_ruas*nilai_ruas*nilai_tinggi;
                        binding.hasilTabung.setText("Hasil : "+String.valueOf(luas));
                    }
                }
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.panjangBalok.setText("");
        binding.lebarBalok.setText("");;
        binding.tinggiBalok.setText("");
        binding.panjangPiramid.setText("");;
        binding.lebarPiramid.setText("");
        binding.tinggiPiramid.setText("");
        binding.ruas.setText("");;
        binding.tinggiTabung.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}