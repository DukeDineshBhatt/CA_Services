package com.technuoma.caservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {

    TextView create_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background));
        setContentView(R.layout.activity_sign_in);


        create_account = findViewById(R.id.txt_create_an_acc);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });

    }
}