package com.chiranjeevi.mvvm.codingassignment.model;


public class LoginResponse {
    private int statusCode;
    private String emailId;
    private String password;

    public LoginResponse(int statusCode){
        this.emailId = emailId;
        this.password = password;
        this.statusCode = statusCode;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public boolean isLogInSucess(){
        return (statusCode == 200);
    }
}
