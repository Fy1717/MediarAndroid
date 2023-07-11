package com.uk.mediar.Service.ApiModel;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.uk.mediar.Model.User;

import org.json.JSONObject;

import java.util.LinkedHashMap;

public class LoginModel {
    public Boolean success;
    public String message;

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


    public JsonObject getData() {
        return data;
    }
    public void setData(JsonObject result) {
        this.data = result;
    }
    private JsonObject data;
}
