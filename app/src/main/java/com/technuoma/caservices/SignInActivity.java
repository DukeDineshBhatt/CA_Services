package com.technuoma.caservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.technuoma.SharePreferenceUtils;
import com.technuoma.caservices.LoginPOJO.Data;
import com.technuoma.caservices.LoginPOJO.LoginBean;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SignInActivity extends AppCompatActivity {

    TextView create_account;
    Button btn_signin;
    EditText et_password, et_email;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.background));
        setContentView(R.layout.activity_sign_in);

        btn_signin = findViewById(R.id.btn_signin);
        et_password = findViewById(R.id.passsword);
        et_email = findViewById(R.id.email);
        progressBar = findViewById(R.id.progressBar);

        create_account = findViewById(R.id.txt_create_an_acc);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if (!email.isEmpty()) {
                    if (!password.isEmpty()) {

                        progressBar.setVisibility(View.VISIBLE);

                        Bean b = (Bean) getApplicationContext();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseurl)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllApiIneterface cr = retrofit.create(AllApiIneterface.class);

                        SiginRequest body = new SiginRequest();
                        SigninRequestData body1 = new SigninRequestData();

                        body1.setEmail(email);
                        body1.setPassword(password);
                        body.setData(body1);
                        body.setAction("login");

                        Call<LoginBean> call = cr.login(body);
                        call.enqueue(new Callback<LoginBean>() {
                            @Override
                            public void onResponse(@NotNull Call<LoginBean> call, @NotNull Response<LoginBean> response) {


                                if (Objects.requireNonNull(response.body()).getStatus().equals("1")) {

                                    Data item = response.body().getData();

                                    SharePreferenceUtils.getInstance().saveString("id", item.getUserId());
                                    SharePreferenceUtils.getInstance().saveString("name", item.getName());
                                    SharePreferenceUtils.getInstance().saveString("email", item.getEmail());
                                    SharePreferenceUtils.getInstance().saveString("mobile", item.getMobile());

                                    progressBar.setVisibility(View.GONE);

                                    Intent intent = new Intent(SignInActivity.this, Cities.class);
                                    //intent.putExtra("id" , item.getId());
                                    startActivity(intent);
                                    finishAffinity();

                                } else {

                                    Toast.makeText(SignInActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                }


                            }

                            @Override
                            public void onFailure(@NotNull Call<LoginBean> call, @NotNull Throwable t) {
                                progressBar.setVisibility(View.GONE);

                                Toast.makeText(SignInActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();

                            }
                        });


                    } else {

                        Toast.makeText(SignInActivity.this, "Please enter correct password", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }


                } else {
                    Toast.makeText(SignInActivity.this, "Please enter correct email", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);


                }

            }
        });
    }
}