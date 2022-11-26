package com.example.listacompras.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.listacompras.R;
import com.example.listacompras.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        
        setContentView(R.layout.activity_home);
    }
}