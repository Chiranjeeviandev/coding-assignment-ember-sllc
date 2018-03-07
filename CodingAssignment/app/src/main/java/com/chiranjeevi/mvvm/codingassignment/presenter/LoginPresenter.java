package com.chiranjeevi.mvvm.codingassignment.presenter;

import com.chiranjeevi.mvvm.codingassignment.model.LoginRequest;
import com.chiranjeevi.mvvm.codingassignment.model.LoginResponse;
import com.chiranjeevi.mvvm.codingassignment.SchedulerProvider;
import com.chiranjeevi.mvvm.codingassignment.model.LoginModel;

import rx.SingleSubscriber;
import rx.Subscriber;


public class LoginPresenter {
    private SchedulerProvider schedulerProvider = null;
    private LoginModel loginModel = null;
    private LoginNavigator loginNavigator = null;

    public LoginPresenter(SchedulerProvider schedulerProvider, LoginModel loginModel) {
        this.loginModel = loginModel;
        this.schedulerProvider = schedulerProvider;
    }

    public void attachView(LoginNavigator loginView) {
        this.loginNavigator = loginView;
    }

    public void validateUser(String emailID, String password) {
        loginModel.checkLogin(new LoginRequest(emailID, password))
                .subscribeOn(schedulerProvider.IO())
                .observeOn(schedulerProvider.UI())
                .subscribe(new Subscriber<LoginResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        loginNavigator.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        loginNavigator.onSuccess();
                    }
                });
    }

    public void getDefaultUserDetails(){
        loginModel.obtainDefaultUser()
                .subscribeOn(schedulerProvider.IO())
                .observeOn(schedulerProvider.UI())
                .subscribe(new SingleSubscriber<LoginRequest>() {
                    @Override
                    public void onSuccess(LoginRequest value) {
                        loginNavigator.defaultUser(value.getEmailId(), value.getPassword());
                    }

                    @Override
                    public void onError(Throwable error) {
                        //Not handling for now.
                    }
                });
    }

}
