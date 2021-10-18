package com.technuoma.caservices;

import com.technuoma.caservices.Category1POJO.Category1Bean;
import com.technuoma.caservices.CitiesPOJO.CitiesBean;
import com.technuoma.caservices.LoginPOJO.LoginBean;
import com.technuoma.caservices.ServicesPOJO.ServicesBean;
import com.technuoma.caservices.SignInPOJO.SignInBean;
import com.technuoma.caservices.SubcategoryPOJO.SubCategoryBean;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AllApiIneterface {


    @POST("api.php")
    Call<LoginBean> login(
            @Body LoginRequest body

    );

    @POST("api.php")
    Call<SignInBean> sigin(
            @Body SiginRequest body

    );

    @POST("api.php")
    Call<CitiesBean> cities(
            @Body CitiesRequest body

    );

    @POST("api.php")
    Call<ServicesBean> services(
            @Body ServicesRequest body

    );

    @POST("api.php")
    Call<Category1Bean> category1(
            @Body Category1Request body

    );

    @POST("api.php")
    Call<SubCategoryBean> subcategory(
            @Body SubCategoryRequest body

    );


}
