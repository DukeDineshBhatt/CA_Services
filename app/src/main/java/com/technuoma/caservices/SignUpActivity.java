package com.technuoma.caservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import com.technuoma.caservices.LoginPOJO.LoginBean;
import com.technuoma.caservices.SignInPOJO.Data;
import com.technuoma.caservices.SignInPOJO.SignInBean;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    TextView go_back;
    Button btn_signup;
    EditText et_name, et_email, et_password, et_phone;
    ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progressBar);

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


                                progressBar.setVisibility(View.VISIBLE);

                                Bean b = (Bean) getApplicationContext();

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.baseurl)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

                                SiginRequest body = new SiginRequest();
                                SiginRequestData body1 = new SiginRequestData();

                                body1.setEmail(email);
                                body1.setPassword(password);
                                body1.setName(name);
                                body1.setPhone(phone);

                                body.setData(body1);
                                body.setAction("register");

                                Call<SignInBean> call = cr.sigin(body);
                                call.enqueue(new Callback<SignInBean>() {
                                    @Override
                                    public void onResponse(@NotNull Call<SignInBean> call, @NotNull Response<SignInBean> response) {

                                        if (Objects.requireNonNull(response.body()).getStatus().equals("1")) {

                                            Data item = response.body().getData();

                                            SharedPreferences mPrefs = getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = mPrefs.edit();
                                            editor.putString("user_id", item.getUserId());
                                            editor.putString("name", item.getName());
                                            editor.putString("email", item.getEmail());
                                            editor.putString("mobile", item.getMobile());
                                            //editor.putBoolean("is_logged_before", true);
                                            editor.commit();

                                            progressBar.setVisibility(View.GONE);

                                            Intent intent = new Intent(SignUpActivity.this, SelectCityActivity.class);
                                            startActivity(intent);
                                            finishAffinity();

                                        } else {

                                            Toast.makeText(SignUpActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.GONE);
                                        }


                                    }

                                    @Override
                                    public void onFailure(@NotNull Call<SignInBean> call, @NotNull Throwable t) {
                                        progressBar.setVisibility(View.GONE);

                                        Toast.makeText(SignUpActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();

                                    }
                                });





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