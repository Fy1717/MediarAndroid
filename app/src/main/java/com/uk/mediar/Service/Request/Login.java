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
import com.uk.mediar.Service.ApiInterface.UserAPI;
import com.uk.mediar.Model.User;
import com.uk.mediar.Service.ApiModel.ErrorHandlerModel;
import com.uk.mediar.Service.ApiModel.LoginModel;

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

public class Login extends ViewModel {
    MutableLiveData<Boolean> status = new MutableLiveData<>();

    public LiveData<Boolean> getLoginStatus(String username, String password) {
        logIn(username, password);

        return status;
    }

    private static Gson gson = new GsonBuilder().setLenient().create();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://93ea-31-223-43-206.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static String defaultErrorMessage = "Error in Login process..";

    private void logIn(String username, String password) {
        try {
            System.out.println("username2 : " + username);
            System.out.println("password2 : " + password);

            RequestBody usernamef = RequestBody.create(MediaType.parse("text/plain"), username);
            RequestBody passwordf = RequestBody.create(MediaType.parse("text/plain"), password);

            UserAPI userAPI = retrofit.create(UserAPI.class);

            retrofit2.Call<ResponseBody> call = userAPI.loginUser(usernamef, passwordf);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        Log.i("LOGIN", "RESPONSE : " + response.toString());

                        ErrorHandlerModel errorHandlerModel = ErrorHandlerModel.getInstance();
                        errorHandlerModel.setLoginErrorMessage(null);

                        if (response.isSuccessful()) {
                            Gson gson = new Gson();
                            LoginModel result = gson.fromJson(response.body().string(), LoginModel.class);

                            ResponseBody myResponse = response.body();

                            JsonObject data = result.getData();

                            Log.i("LOGIN", "SUCCESS");

                            System.out.println("LOGINMODEL STRING: " + myResponse.toString());
                            System.out.println("LOGINMODEL SUCCESS: " + result.getSuccess());
                            System.out.println("LOGINMODEL DATA: " + result.getData());

                            System.out.println("LOGINMODEL FOLLOWERS: " + result.getData());

                            try {
                                String email = String.valueOf(data.get("email"));
                                String token = String.valueOf(data.get("token"));
                                String username = String.valueOf(data.get("username"));
                                String image = String.valueOf(data.get("image")).replace("\"","");
                                String name = String.valueOf(data.get("name"));
                                JsonArray starredShares =  data.get("starred_shares").getAsJsonArray();
                                JsonArray followers = data.get("followers").getAsJsonArray();
                                JsonArray followings = data.get("followings").getAsJsonArray();
                                JsonArray posts = data.get("shares").getAsJsonArray();
                                double totalPoints = data.get("totalPoints").getAsDouble();

                                System.out.println("LOGINMODEL DATA TYPE: " + data.getClass());
                                System.out.println("LOGINMODEL email: " + email);
                                System.out.println("LOGINMODEL token: " + token);
                                System.out.println("LOGINMODEL username: " + username);
                                System.out.println("LOGINMODEL name: " + name);
                                System.out.println("LOGINMODEL starredShares: " + starredShares);
                                System.out.println("LOGINMODEL followers: " + followers);
                                System.out.println("LOGINMODEL followings: " + followings);
                                System.out.println("LOGINMODEL posts: " + posts);
                                System.out.println("LOGINMODEL totalPoints: " + totalPoints);
                                System.out.println("LOGINMODEL image: " + image);

                                User user = User.getInstance();
                                user.setEmail(email.replaceAll("\"",""));
                                user.setUsername(username.replaceAll("\"",""));
                                user.setToken(token);
                                user.setImage(image);
                                user.setName(name.replaceAll("\"",""));
                                user.setStarredShares(starredShares);
                                user.setFollowings(followings);
                                user.setFollowers(followers);
                                user.setPosts(posts);
                                user.setTotalPoints(totalPoints);

                                errorHandlerModel.setLoginErrorMessage(null);

                                status.setValue(true);
                            } catch (Exception e) {
                                System.out.println("ERROR: " + e.getMessage());
                            }
                        } else {
                            String errorMsg = "";

                            try {
                                JSONObject errorJsonObj = new JSONObject(response.errorBody().string());
                                errorMsg = errorJsonObj.getString("message");

                                System.out.println("MESSAGE : " + errorMsg);
                                errorHandlerModel.setLoginErrorMessage(errorMsg);
                            } catch (JSONException e) {
                                Log.e("LOGIN", "ERROR1 : " + e.getLocalizedMessage());

                                errorHandlerModel.setLoginErrorMessage(defaultErrorMessage);
                            }

                            status.setValue(false);
                        }
                    } catch (Exception e) {
                        Log.e("LOGIN", "ERROR2 : " + e.getLocalizedMessage());

                        status.setValue(false);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("LOGIN", "ERROR3 : " + t.getLocalizedMessage());
                }
            });
        } catch (Exception e) {
            Log.e("LOGIN", "ERROR4 : " + e.getLocalizedMessage());
        }
    }
}
