package com.uk.mediar.Service.ApiModel;

public class LogoutModel {
    private static LogoutModel logoutModel;

    private Boolean logoutSuccess;
    private String errorMessage;

    public Boolean getLogoutSuccess() {
        return logoutSuccess;
    }

    public void setLogoutSuccess(Boolean logoutSuccess) {
        this.logoutSuccess = logoutSuccess;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static LogoutModel getInstance() {
        if (logoutModel == null) {
            logoutModel = new LogoutModel();
        }

        return logoutModel;
    }
}
