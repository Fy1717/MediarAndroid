package com.uk.mediar.Service.Request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uk.mediar.Service.ApiInterface.LoginAPI;
import com.uk.mediar.Service.ApiModel.LoginModel;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends ViewModel {
    MutableLiveData<Boolean> status = new MutableLiveData<>();

    public LiveData<Boolean> getLoginStatus(String username, String password) {
        logIn(username, password);

        return status;
    }

    private static Gson gson = new GsonBuilder().setLenient().create();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://343a-2a02-4e0-2d22-12d-f18d-fc66-d53e-ce07.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static String defaultErrorMessage = "Error in Login process..";

    private void logIn(String username, String password) {
        try {
            System.out.println("username2 : " + username);
            System.out.println("password2 : " + password);

            RequestBody usernamef = RequestBody.create(MediaType.parse("text/plain"), username);
            RequestBody passwordf = RequestBody.create(MediaType.parse("text/plain"), password);

            LoginAPI loginAPI = retrofit.create(LoginAPI.class);

            retrofit2.Call<LoginModel> call = loginAPI.loginUser(usernamef, passwordf);

            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    try {
                        Log.i("LOGIN", "RESPONSE : " + response.body());

                        LoginModel loginModel = LoginModel.getInstance();
                        loginModel = response.body();

                        if (response.isSuccessful()) {
                            Log.i("LOGIN", "SUCCESS");

                            loginModel.setLoginSuccess(true);
                            loginModel.setErrorMessage(null);

                            status.setValue(true);
                        } else {
                            Log.e("LOGIN", "FAIL : " + response.errorBody().string());

                            //loginModel.setErrorMessage(defaultErrorMessage);

                            status.setValue(false);
                        }
                    } catch (Exception e) {
                        status.setValue(false);
                    }
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    Log.e("LOGIN", "ERROR : " + t.getLocalizedMessage());
                }
            });
        } catch (Exception e) {
            Log.e("LOGIN", "ERROR : " + e.getLocalizedMessage());
        }
    }
}
