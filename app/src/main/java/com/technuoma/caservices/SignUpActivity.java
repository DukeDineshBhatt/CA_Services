package com.technuoma.caservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {
    TextView go_back;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background));
        setContentView(R.layout.activity_sign_up);

        go_back = findViewById(R.id.txt_go_back);
        btn_signup = findViewById(R.id.btn_signup);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUpActivity.this,SelectCityActivity.class);
                startActivity(intent);


            }
        });
    }
}