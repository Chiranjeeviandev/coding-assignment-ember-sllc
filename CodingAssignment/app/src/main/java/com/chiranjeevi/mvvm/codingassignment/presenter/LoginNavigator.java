package com.chiranjeevi.mvvm.codingassignment.presenter;


public interface LoginNavigator {

    void onSuccess();

    void onError(String failMessage);

    void defaultUser(String emailId, String password);
}
