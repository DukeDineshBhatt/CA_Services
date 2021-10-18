package com.technuoma.caservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubCategoryRequest {
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("data")
    @Expose
    private SubCategoryRequestData data;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SubCategoryRequestData getData() {
        return data;
    }

    public void setData(SubCategoryRequestData data) {
        this.data = data;
    }

}
