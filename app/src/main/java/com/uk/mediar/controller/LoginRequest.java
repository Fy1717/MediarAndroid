package com.uk.mediar.controller;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginRequest extends AsyncTask<String, String, String> {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected String doInBackground(String... uri) {
        String responseString = null;
        String urlParameter = "https://343a-2a02-4e0-2d22-12d-f18d-fc66-d53e-ce07.ngrok-free.app/api/users/login";
        String email = uri[0].trim();
        String password = uri[1].trim();

        String tokenFromApi = null;
        Response response = null;

        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            try {
                RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("username",email)
                        .addFormDataPart("password",password)
                        .build();

                Request request = new Request.Builder()
                        .url(urlParameter)
                        .method("POST", body)
                        .build();

                response = client.newCall(request).execute();

                if (response.code() == 200) {
                    tokenFromApi = response.body().string();

                    System.out.println("RESPONSE : " + tokenFromApi);
                    //User.MainToken = tokenFromApi;
                    //responseString = User.MainToken;
                    Log.i("ADDUSER", "TOKEN GETTED");
                } else {
                    Log.e("ADDUSER", "ERROR : " + response.code());
                }
            } catch (Exception e) {
                Log.e("ADDUSER", "ERROR : " + e);
            }
        } catch (Exception e) {
            Log.e("ADDUSER", "ERROR : " + e);
        }

        return responseString;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}

