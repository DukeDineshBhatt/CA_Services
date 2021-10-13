package com.technuoma.caservices;

import com.technuoma.caservices.LoginPOJO.LoginBean;
import com.technuoma.caservices.SignInPOJO.SignInBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AllApiIneterface {


    @POST("api.php")
    Call<LoginBean> login(
            @Body SiginRequest body

    );


}
