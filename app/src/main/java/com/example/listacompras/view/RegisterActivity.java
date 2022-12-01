package com.example.listacompras.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.listacompras.databinding.ActivityRegisterBinding;
import com.example.listacompras.presenter.LoginPresenterContract;

public class RegisterActivity extends AppCompatActivity implements LoginPresenterContract.view {

    private ActivityRegisterBinding binding;
    private LoginPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenter.register(binding.edtRegisterLogin.getText().toString(),
                            binding.edtRegisterPassword.getText().toString(),
                            binding.edtRegisterNewPassword.getText().toString());
                }
                catch (Exception e) {
                    message("Preencha todos os campos");
                }
            }
        });
    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void entrar(Class mClass) {
        startActivity(new Intent(this, mClass));
    }
}