package com.technuoma.caservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CitiesRequest {
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("data")
    @Expose
    private CitiesRequestData data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public CitiesRequestData getData() {
        return data;
    }

    public void setData(CitiesRequestData data) {
        this.data = data;
    }

}
