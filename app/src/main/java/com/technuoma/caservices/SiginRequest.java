package com.technuoma.caservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiginRequest {

    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("data")
    @Expose
    private SigninRequestData data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SigninRequestData getData() {
        return data;
    }

    public void setData(SigninRequestData data) {
        this.data = data;
    }

}