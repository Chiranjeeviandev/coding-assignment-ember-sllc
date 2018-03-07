package com.chiranjeevi.mvvm.codingassignment;

import com.chiranjeevi.mvvm.codingassignment.model.LoginModel;
import com.chiranjeevi.mvvm.codingassignment.model.LoginRequest;
import com.chiranjeevi.mvvm.codingassignment.model.LoginResponse;
import com.chiranjeevi.mvvm.codingassignment.presenter.LoginNavigator;
import com.chiranjeevi.mvvm.codingassignment.presenter.LoginPresenter;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginUnitTest {

    @Test
    public void checkLogin(){
        LoginModel loginModel=new LoginModel();
        LoginModel spyLoginModel=spy(loginModel);
        SchedulerProvider schedulerProvider=new TestSchedulerProvider();
        LoginNavigator testView= Mockito.mock(LoginNavigator.class);
        LoginRequest defaultLogIn= Mockito.mock(LoginRequest.class);
        LoginPresenter loginPresenter=new LoginPresenter(schedulerProvider,spyLoginModel);
        loginPresenter.attachView(testView);
        when(spyLoginModel.getDefaultLoginApi()).thenReturn(defaultLogIn);
        verify(testView).defaultUser("adbdef@test","1234567");
        when(spyLoginModel.loginApi(new LoginRequest("abc", "1234455"))).thenReturn(new LoginResponse(200));
        when(spyLoginModel.loginApi(new LoginRequest("abc@sdswe", "12defefv"))).thenReturn(new LoginResponse(204));
        loginPresenter.validateUser("test","123");
        verify(testView).onSuccess();
        loginPresenter.validateUser("test","124");
        verify(testView).onError("Invalid Credentials");
    }
}