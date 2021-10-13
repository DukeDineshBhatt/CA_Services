package com.technuoma.caservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    TextView go_back;
    Button btn_signup;
    EditText et_name, et_email, et_password, et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background));
        setContentView(R.layout.activity_sign_up);

        go_back = findViewById(R.id.txt_go_back);
        btn_signup = findViewById(R.id.btn_signup);
        et_name = findViewById(R.id.name);
        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);
        et_phone = findViewById(R.id.phone);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                String name = et_name.getText().toString();
                String phone = et_phone.getText().toString();


                if (!name.isEmpty()) {
                    if (!email.isEmpty()) {
                        if (!password.isEmpty()) {
                            if (!phone.isEmpty()) {

                                 /* Intent intent = new Intent(SignUpActivity.this,SelectCityActivity.class);
                                    startActivity(intent);*/
                                Toast.makeText(SignUpActivity.this, "Ok", Toast.LENGTH_SHORT).show();

                            } else {

                                et_phone.setError("Enter mobile number");
                                et_phone.requestFocus();
                                return;

                            }

                        } else {

                            et_password.setError("Enter password");
                            et_password.requestFocus();
                            return;
                        }
                    } else {

                        et_email.setError("Enter email");
                        et_email.requestFocus();
                        return;
                    }
                } else {

                    et_name.setError("Enter name");
                    et_name.requestFocus();
                    return;
                }

            }
        });
    }
}