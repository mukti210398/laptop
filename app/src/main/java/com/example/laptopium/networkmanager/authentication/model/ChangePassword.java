package com.example.laptopium.networkmanager.authentication.model;

public class ChangePassword {
    private String userMail;
    private String oldUserPswd;
    private String newUserPswd;

    public ChangePassword() {
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getOldUserPswd() {
        return oldUserPswd;
    }

    public void setOldUserPswd(String oldUserPswd) {
        this.oldUserPswd = oldUserPswd;
    }

    public String getNewUserPswd() {
        return newUserPswd;
    }

    public void setNewUserPswd(String newUserPswd) {
        this.newUserPswd = newUserPswd;
    }
}
