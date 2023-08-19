package com.uk.mediar.Service.Request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uk.mediar.Model.Share;
import com.uk.mediar.Model.User;
import com.uk.mediar.Service.ApiInterface.PostAPI;
import com.uk.mediar.Service.ApiInterface.UserAPI;
import com.uk.mediar.Model.User;
import com.uk.mediar.Service.ApiModel.ErrorHandlerModel;
import com.uk.mediar.Service.ApiModel.LoginModel;
import com.uk.mediar.Service.ApiModel.LogoutModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StarPost extends ViewModel {
    ErrorHandlerModel errorHandlerModel = ErrorHandlerModel.getInstance();

    MutableLiveData<Boolean> status = new MutableLiveData<>();

    public LiveData<Boolean> getStarPostStatus(String token, String postId, Boolean star) {
        starPost(token, postId, star);

        return status;
    }

    private static Gson gson = new GsonBuilder().setLenient().create();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://f295-31-223-50-99.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static String defaultErrorMessage = "Error in Star process..";

    private void starPost(String token, String postId, Boolean star) {
        try {
            System.out.println("PostId : " + postId);
            System.out.println("StarOrUnstar : " + star);

            RequestBody postIdf = RequestBody.create(MediaType.parse("text/plain"), postId);

            PostAPI postAPI = retrofit.create(PostAPI.class);

            retrofit2.Call<ResponseBody> call = null;

            if (star) {
                call = postAPI.starPost(token.replace("\"", ""), postIdf);
            } else {
                call = postAPI.starPostBack(token.replace("\"", ""), postIdf);
            }

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        Log.i("STAR", "RESPONSE : " + response.toString());

                        errorHandlerModel.setStarPostErrorMessage(null);

                        if (response.isSuccessful()) {
                            errorHandlerModel.setStarPostErrorMessage(null);

                            status.setValue(true);
                        } else {
                            errorHandlerModel.setStarPostErrorMessage(defaultErrorMessage);

                            status.setValue(false);
                        }
                    } catch (Exception e) {
                        errorHandlerModel.setStarPostErrorMessage(defaultErrorMessage);

                        Log.e("STAR", "ERROR2 : " + e.getLocalizedMessage());

                        status.setValue(false);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    errorHandlerModel.setStarPostErrorMessage(defaultErrorMessage);

                    Log.e("STAR", "ERROR3 : " + t.getLocalizedMessage());
                }
            });
        } catch (Exception e) {
            errorHandlerModel.setStarPostErrorMessage(defaultErrorMessage);

            Log.e("STAR", "ERROR4 : " + e.getLocalizedMessage());
        }
    }
}
