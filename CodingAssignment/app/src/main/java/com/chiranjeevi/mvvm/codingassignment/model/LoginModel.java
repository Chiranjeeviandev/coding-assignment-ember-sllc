package com.chiranjeevi.mvvm.codingassignment.model;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;


public class LoginModel {
    private final static String DEFAULT_DUMMY_EMAIL_ID = "chiranj@codingtest.com";
    private final static String DEFAULT_DUMMY_PASSWORD = "chiranj.codingtest";

    public Observable<LoginResponse> checkLogin(final LoginRequest loginRequest) {
        return Observable.create(new Observable.OnSubscribe<LoginResponse>() {
            @Override
            public void call(Subscriber<? super LoginResponse> subscriber) {
                LoginResponse loginResponse=loginApi(loginRequest);
                if(loginResponse.isLogInSucess()){
                    subscriber.onNext(loginResponse);
                    subscriber.onCompleted();
                }else{
                    subscriber.onError(new Throwable("Invalid Login Error: " + loginResponse.getStatusCode()));
                    subscriber.onCompleted();
                }
            }
        });

    }

    public Single<LoginRequest> obtainDefaultUser() {
        return Single.create(new Single.OnSubscribe<LoginRequest>() {
            @Override
            public void call(SingleSubscriber<? super LoginRequest> singleSubscriber) {
                LoginRequest defaultLogin = getDefaultLoginApi();
                singleSubscriber.onSuccess(defaultLogin);
            }
        });

    }

    public LoginResponse loginApi(LoginRequest loginRequest){
        int errorCode=400;
        LoginResponse response = new LoginResponse(errorCode);
        try {
            // Simulate network access.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return response;
        }

        if(DEFAULT_DUMMY_EMAIL_ID.equals(loginRequest.getEmailId()) && DEFAULT_DUMMY_PASSWORD.equals(loginRequest.getPassword())) {
            response.setStatusCode(200);
        }

        return response;
    }

    public LoginRequest getDefaultLoginApi(){
        return new LoginRequest(DEFAULT_DUMMY_EMAIL_ID,DEFAULT_DUMMY_PASSWORD);
    }

}


