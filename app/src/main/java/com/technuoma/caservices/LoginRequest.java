package com.technuoma.caservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("data")
    @Expose
    private LoginRequestData data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LoginRequestData getData() {
        return data;
    }

    public void setData(LoginRequestData data) {
        this.data = data;
    }

}