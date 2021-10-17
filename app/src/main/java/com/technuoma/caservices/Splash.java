package com.technuoma.caservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background));
        setContentView(R.layout.activity_splash);


        SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0);

        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);
        boolean hasCity = settings.getBoolean("hasCity", false);

        if (hasCity) {
            //Go directly to main activity.

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    Intent i = new Intent(Splash.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }, SPLASH_TIME_OUT);
        } else if (hasLoggedIn) {

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    Intent i = new Intent(Splash.this, SelectCityActivity.class);
                    startActivity(i);
                    finish();

                }
            }, SPLASH_TIME_OUT);

        } else {


            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    Intent i = new Intent(Splash.this, SignInActivity.class);
                    startActivity(i);
                    finish();

                }
            }, SPLASH_TIME_OUT);


        }


    }


}

