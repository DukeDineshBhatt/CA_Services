package com.technuoma.caservices;

import android.app.Application;

public class Bean extends Application {

    public final String baseurl = "http://ec2-3-108-105-236.ap-south-1.compute.amazonaws.com/ca/";
    @Override
    public void onCreate() {
        super.onCreate();

    }

}
