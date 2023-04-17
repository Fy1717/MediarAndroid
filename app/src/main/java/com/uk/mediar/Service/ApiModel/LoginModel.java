package com.uk.mediar.Service.ApiModel;

public class LoginModel {
    private static LoginModel loginModel;

    private Boolean loginSuccess;
    private String errorMessage;


    public Boolean getLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(Boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static LoginModel getInstance() {
        if (loginModel == null) {
            loginModel = new LoginModel();
        }

        return loginModel;
    }
}
