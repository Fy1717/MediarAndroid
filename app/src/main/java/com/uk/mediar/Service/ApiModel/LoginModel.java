package com.uk.mediar.Service.ApiModel;

import com.google.gson.annotations.SerializedName;
import com.uk.mediar.Model.User;

import org.json.JSONObject;

import java.util.LinkedHashMap;

public class LoginModel {
    @SerializedName("success")
    public Boolean success;
    @SerializedName("message")
    public String message;

    @SerializedName("data")
    public LinkedHashMap<String, Object> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LinkedHashMap<String, Object> getData() {
        return data;
    }

    public void setData(LinkedHashMap data) {
        this.data = data;
    }
}
