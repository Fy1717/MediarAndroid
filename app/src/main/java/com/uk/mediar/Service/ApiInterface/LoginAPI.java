package com.uk.mediar.Service.ApiInterface;

import com.uk.mediar.Service.ApiModel.LoginModel;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface LoginAPI {
    @Multipart
    @POST("api/users/login")
    Call<LoginModel> loginUser(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password
    );
}