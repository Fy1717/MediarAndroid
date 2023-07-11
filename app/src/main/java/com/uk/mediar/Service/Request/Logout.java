package com.uk.mediar.Service.Request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uk.mediar.Service.ApiInterface.UserAPI;
import com.uk.mediar.Service.ApiModel.ErrorHandlerModel;
import com.uk.mediar.Service.ApiModel.LogoutModel;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Logout extends ViewModel {
    MutableLiveData<Boolean> status = new MutableLiveData<>();

    public LiveData<Boolean> getLogoutStatus(String token) {
        logOut(token);

        return status;
    }

    private static Gson gson = new GsonBuilder().setLenient().create();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://93ea-31-223-43-206.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static String defaultErrorMessage = "Error in Logout process..";

    private void logOut(String token) {
        try {
            UserAPI userAPI = retrofit.create(UserAPI.class);

            retrofit2.Call<LogoutModel> call = userAPI.logoutUser(token);

            call.enqueue(new Callback<LogoutModel>() {
                @Override
                public void onResponse(Call<LogoutModel> call, Response<LogoutModel> response) {
                    try {
                        Log.i("LOGOUT", "RESPONSE : " + response.body());

                        ErrorHandlerModel errorHandlerModel = ErrorHandlerModel.getInstance();
                        errorHandlerModel.setLogoutErrorMessage(null);

                        LogoutModel logoutModel = LogoutModel.getInstance();
                        logoutModel = response.body();

                        if (response.isSuccessful()) {
                            Log.i("LOGOUT", "SUCCESS");

                            logoutModel.setLogoutSuccess(true);
                            logoutModel.setErrorMessage(null);

                            status.setValue(true);
                        } else {
                            Log.e("LOGOUT", "FAIL : " + response.errorBody().string());

                            String errorMsg = "";

                            try {
                                JSONObject errorJsonObj = new JSONObject(response.errorBody().string());
                                errorMsg = errorJsonObj.getString("message");

                                System.out.println("MESSAGE : " + errorMsg);
                                errorHandlerModel.setLogoutErrorMessage(errorMsg);
                            } catch (JSONException e) {
                                Log.e("LOGOUT", "ERROR1 : " + e.getLocalizedMessage());

                                errorHandlerModel.setLogoutErrorMessage(defaultErrorMessage);
                            }

                            status.setValue(false);
                        }
                    } catch (Exception e) {
                        status.setValue(false);
                    }
                }

                @Override
                public void onFailure(Call<LogoutModel> call, Throwable t) {
                    Log.e("LOGOUT", "ERROR1 : " + t.getLocalizedMessage());
                }
            });
        } catch (Exception e) {
            Log.e("LOGOUT", "ERROR2 : " + e.getLocalizedMessage());
        }
    }
}
