package com.technuoma.caservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesRequest {
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("data")
    @Expose
    private ServicesRequestData data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ServicesRequestData getData() {
        return data;
    }

    public void setData(ServicesRequestData data) {
        this.data = data;
    }

}
