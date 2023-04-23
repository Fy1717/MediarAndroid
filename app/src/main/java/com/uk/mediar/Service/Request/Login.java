package com.uk.mediar.Service.Request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
            .baseUrl("https://ac8e-46-155-48-213.ngrok-free.app/")
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

            retrofit2.Call<LoginModel> call = userAPI.loginUser(usernamef, passwordf);

            call.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    try {
                        Log.i("LOGIN", "RESPONSE : " + response.toString());

                        ErrorHandlerModel errorHandlerModel = ErrorHandlerModel.getInstance();
                        errorHandlerModel.setLoginErrorMessage(null);

                        if (response.isSuccessful()) {
                            LoginModel loginModel = response.body();

                            Log.i("LOGIN", "SUCCESS");

                            System.out.println("LOGINMODEL STRING: " + loginModel.toString());
                            System.out.println("LOGINMODEL SUCCESS: " + loginModel.getSuccess());
                            System.out.println("LOGINMODEL MESSAGE: " + loginModel.getMessage());

                            LinkedHashMap<String, Object> data = loginModel.getData();
                            String email = (String) data.get("email");
                            String token = (String) data.get("token");
                            String username = (String) data.get("username");
                            String image = (String) data.get("image");
                            String name = (String) data.get("name");
                            ArrayList<Share> starredShares = (ArrayList<Share>) data.get("starred_shares");
                            ArrayList followers = (ArrayList) data.get("followers");
                            ArrayList followings = (ArrayList) data.get("followings");
                            ArrayList<Share> posts = (ArrayList<Share>) data.get("shares");
                            double totalPoints = (double) data.get("totalPoints");

                            System.out.println("LOGINMODEL DATA: " + data);
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

                            User user = User.getInstance();
                            user.setEmail(email);
                            user.setUsername(username);
                            user.setToken(token);
                            user.setImage(image);
                            user.setName(name);
                            user.setStarredShares(starredShares);
                            user.setFollowings(followings);
                            user.setFollowers(followers);
                            user.setPosts(posts);
                            user.setTotalPoints(totalPoints);

                            errorHandlerModel.setLoginErrorMessage(null);

                            status.setValue(true);
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
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    Log.e("LOGIN", "ERROR3 : " + t.getLocalizedMessage());
                }
            });
        } catch (Exception e) {
            Log.e("LOGIN", "ERROR4 : " + e.getLocalizedMessage());
        }
    }
}
