package com.example.laptopium.networkmanager.authentication.model;

import java.io.Serializable;

public class LoginUser implements Serializable {
    private String userMail;
    private String userPswd;

    public LoginUser(String userMail, String userPassword) {
        this.userMail = userMail;
        this.userPswd = userPassword;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }
}
