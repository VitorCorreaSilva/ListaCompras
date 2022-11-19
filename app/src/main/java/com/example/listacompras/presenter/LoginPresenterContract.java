package com.example.listacompras.presenter;

import android.app.Activity;

public class LoginPresenterContract {

    public interface view {
        public void message(String msg);
        public Activity getActivity();
        public void preferencesUserUpdate(int userId);
    }

    public interface presenter {
        public void checkLogin(String login, String password);
    }
}
