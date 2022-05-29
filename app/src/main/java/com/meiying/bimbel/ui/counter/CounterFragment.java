package com.meiying.bimbel.ui.counter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.meiying.bimbel.config.PrefManager;
import com.meiying.bimbel.databinding.FragmentCounterBinding;

public class CounterFragment extends Fragment {

    private FragmentCounterBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCounterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final int[] nilai = {PrefManager.getInstance(getActivity()).getInt("nilai")};
        binding.display.setText(String.valueOf(nilai[0]));
        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nilai[0]++;
                PrefManager.getInstance(getActivity()).put("nilai", nilai[0]);
                binding.display.setText(String.valueOf(nilai[0]));
            }
        });

        binding.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nilai[0]--;
                PrefManager.getInstance(getActivity()).put("nilai", nilai[0]);
                binding.display.setText(String.valueOf(nilai[0]));
            }
        });

        binding.reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nilai[0]=0;
                PrefManager.getInstance(getActivity()).put("nilai", nilai[0]);
                binding.display.setText(String.valueOf(nilai[0]));
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}