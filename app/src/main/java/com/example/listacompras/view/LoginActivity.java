package com.example.listacompras.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.listacompras.databinding.ActivityLoginBinding;
import com.example.listacompras.presenter.LoginPresenter;
import com.example.listacompras.presenter.LoginPresenterContract;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LoginPresenterContract.view {

    private ActivityLoginBinding binding;
    private GoogleSignInClient googleSignInClient;
    private LoginPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.presenter = new LoginPresenter(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view =  binding.getRoot();
        setContentView(view);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LOGIN", binding.edtLogin.getText().toString());
                Log.d("SENHA", binding.edtPassword.getText().toString());
                presenter.checkLogin( binding.edtLogin.getText().toString(),
                        binding.edtPassword.getText().toString());
            }
        });
        
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrar(RegisterActivity.class);
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