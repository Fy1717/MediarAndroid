package com.uk.mediar.Service.ApiModel;


public class ErrorHandlerModel {
    public static ErrorHandlerModel errorHandlerModel;

    public static String loginErrorMessage;
    public static String logoutErrorMessage;
    public static String starPostErrorMessage;


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

    public static String getStarPostErrorMessage() {
        return starPostErrorMessage;
    }

    public static void setStarPostErrorMessage(String starPostErrorMessage) {
        ErrorHandlerModel.starPostErrorMessage = starPostErrorMessage;
    }

    public static ErrorHandlerModel getInstance() {
        if (errorHandlerModel == null) {
            errorHandlerModel = new ErrorHandlerModel();
        }

        return errorHandlerModel;
    }

}
