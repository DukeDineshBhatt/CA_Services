package com.technuoma.caservices;

import android.app.Application;
import android.content.Context;

public class Bean extends Application {

    private static Context context;

    public final String baseurl = "http://ec2-3-108-105-236.ap-south-1.compute.amazonaws.com/ca/";


    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

}
