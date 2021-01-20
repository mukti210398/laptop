package com.example.laptopium.networkmanager.authentication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateUserId implements Serializable {
    @SerializedName("userId")
    private String userId;
    @SerializedName("macAddress")
    private String macAddress;

    public UpdateUserId() {
    }

    public UpdateUserId(String userId, String macAddress) {
        this.userId = userId;
        this.macAddress = macAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
