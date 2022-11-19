package com.example.listacompras.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.listacompras.model.UserModel;
import com.example.listacompras.service.LoginService;
import com.example.listacompras.view.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements LoginPresenterContract.presenter {
    private LoginPresenterContract.view activity;

    public LoginPresenter(LoginPresenterContract.view activity) {
        this.activity = activity;
    }

    @Override
    public void checkLogin(String login, String password) {
        
    }
}
