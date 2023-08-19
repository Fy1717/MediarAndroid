package com.uk.mediar.Service.ApiInterface;

import com.uk.mediar.Service.ApiModel.LogoutModel;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserAPI {
    @Multipart
    @POST("api/users/login")
    Call<ResponseBody> loginUser(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password
    );

    @GET("api/users/logout")
    Call<LogoutModel> logoutUser(
            @Header("token") String token
    );
}