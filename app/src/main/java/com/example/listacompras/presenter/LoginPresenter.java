package com.example.listacompras.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenter implements LoginPresenterContract.presenter{
    private LoginPresenterContract.view view;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    public LoginPresenter(LoginPresenterContract.view view) {
        this.view = view;
        mAuth = FirebaseAuth.getInstance();
        this.user = mAuth.getCurrentUser();
        if (user != null) {
            mAuth.signOut();
            this.user = mAuth.getCurrentUser();
        }
    }


    @Override
    public void checkLogin(String login, String password) {
        mAuth.signInWithEmailAndPassword(login, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        user = mAuth.getCurrentUser();
                        view.message("Authentication successfuly.");
                        view.entrar();
                    } else {
                        view.message("signInWithEmail:failure"+ task.getException());
                    }
                }
            });
    }

    @Override
    public void register(String email, String password, String newPassword) {
        if (password.equals(newPassword)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           user = mAuth.getCurrentUser();
                           view.message("Cadastro realizado com sucesso");
                           view.entrar();
                       } else {
                           view.message("falha no cadastro, " + task.getException().getMessage());

                       }
                   }
               }
            );
        } else {
            view.message("Senhas não conferem");
        }
    }
}
