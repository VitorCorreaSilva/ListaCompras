package com.example.listacompras.model;

public class UserModel {
    private int id;
    private String name;
    private String userLogin;
    private String password;

    public UserModel(int id, String name, String userLogin, String password)
    {
        this.id = id;
        this.name = name;
        this.userLogin = userLogin;
        this.password = password;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLogin() { return userLogin; }

    public void setLogin(String userLogin) { this.userLogin = userLogin; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

}
