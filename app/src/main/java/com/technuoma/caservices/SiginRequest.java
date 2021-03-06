package com.technuoma.caservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SiginRequest {
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("data")
    @Expose
    private SiginRequestData data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SiginRequestData getData() {
        return data;
    }

    public void setData(SiginRequestData data) {
        this.data = data;
    }

}
