package com.technuoma.caservices;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicesRequestData {
    @SerializedName("userId")
    @Expose
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
