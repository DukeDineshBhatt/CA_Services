package com.technuoma.caservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category1Request {
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("data")
    @Expose
    private Category1RequestData data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Category1RequestData getData() {
        return data;
    }

    public void setData(Category1RequestData data) {
        this.data = data;
    }

}
