package com.uk.mediar.Service.ApiInterface;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PostAPI {
    @Multipart
    @POST("api/users/point_share")
    Call<ResponseBody> starPost(
            @Header("token") String token,
            @Part("shareId") RequestBody shareId
    );

    @Multipart
    @POST("api/users/point_share_back")
    Call<ResponseBody> starPostBack(
            @Header("token") String token,
            @Part("shareId") RequestBody shareId
    );
}