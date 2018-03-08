package com.chiranjeevi.mvvm.codingassignment;

import com.chiranjeevi.mvvm.codingassignment.model.LoginModel;
import com.chiranjeevi.mvvm.codingassignment.model.LoginRequest;
import com.chiranjeevi.mvvm.codingassignment.model.LoginResponse;
import com.chiranjeevi.mvvm.codingassignment.presenter.LoginNavigator;
import com.chiranjeevi.mvvm.codingassignment.presenter.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import rx.Observable;
import rx.Single;
import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginUnitTest {

    @Mock
    public LoginModel mLoginModel;

    @Spy
    public LoginModel mSpyLoginModel;

    @Mock
    public LoginNavigator mTestNavigator;

    private LoginPresenter mTestLoginPresenter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        SchedulerProvider schedulerProvider=new TestSchedulerProvider();
        mTestLoginPresenter = new LoginPresenter(schedulerProvider,mSpyLoginModel);
        mTestLoginPresenter.attachView(mTestNavigator);
    }

    @Test
    public void checkDefaultUser(){
        LoginRequest loginRequest = new LoginRequest("asdassd","123123123");
        doReturn(Single.just(loginRequest)).when(mSpyLoginModel).obtainDefaultUser();
        mTestNavigator.defaultUser(loginRequest.getEmailId(),loginRequest.getPassword());
        verify(mTestNavigator).defaultUser("asdassd" , "123123123");
    }

    @Test
    public void checkFailLogin(){
        doReturn(Observable.just(new LoginResponse(400))).when(mSpyLoginModel).checkLogin(new LoginRequest("test", "123"));

        mTestLoginPresenter.validateUser("test","123");
        verify(mTestNavigator).onError("Invalid Login Error: 400");

    }

    @Test
    public void checkSucessLogIn(){
        doReturn(Observable.just(new LoginResponse(200))).when(mSpyLoginModel).checkLogin(new LoginRequest("chiranj@codingtest.com", "chiranj.codingtest"));

        mTestLoginPresenter.validateUser("chiranj@codingtest.com","chiranj.codingtest");
        verify(mTestNavigator).onSuccess(200);
    }

}