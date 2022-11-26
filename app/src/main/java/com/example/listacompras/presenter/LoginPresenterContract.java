package com.example.listacompras.presenter;

import android.app.Activity;
import android.content.Context;

public class LoginPresenterContract {

    public interface view {
        public void message(String msg);
        public Context getContext();
        public void entrar();
    }

    public interface presenter {
        public void checkLogin(String login, String password);
        public void register(String email, String password, String newPassword);
    }
}
