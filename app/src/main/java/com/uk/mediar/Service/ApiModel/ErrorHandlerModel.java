package com.uk.mediar.Service.ApiModel;

import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

public class ErrorHandlerModel {
    public static ErrorHandlerModel errorHandlerModel;

    public static String loginErrorMessage;
    public static String logoutErrorMessage;


    public static String getLoginErrorMessage() {
        return loginErrorMessage;
    }

    public static void setLoginErrorMessage(String loginErrorMessage) {
        ErrorHandlerModel.loginErrorMessage = loginErrorMessage;
    }

    public static String getLogoutErrorMessage() {
        return logoutErrorMessage;
    }

    public static void setLogoutErrorMessage(String logoutErrorMessage) {
        ErrorHandlerModel.logoutErrorMessage = logoutErrorMessage;
    }


    public static ErrorHandlerModel getInstance() {
        if (errorHandlerModel == null) {
            errorHandlerModel = new ErrorHandlerModel();
        }

        return errorHandlerModel;
    }

}
