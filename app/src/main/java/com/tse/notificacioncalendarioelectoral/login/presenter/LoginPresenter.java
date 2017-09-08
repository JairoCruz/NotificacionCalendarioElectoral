package com.tse.notificacioncalendarioelectoral.login.presenter;

/**
 * Created by TSE on 31/8/2017.
 */

public class LoginPresenter implements LoginContract.Presenter, LoginInteractor.Callback {

    private final LoginContract.View mLoginView;
    private LoginInteractor mLoginInteractor;

    public LoginPresenter(LoginContract.View loginView, LoginInteractor loginInteractor){
        mLoginView = loginView;
        loginView.setPresenter(this);
        mLoginInteractor = loginInteractor;
    }


    @Override
    public void start() {

    }

    @Override
    public void attemptLogin(String email, String password) {
        mLoginView.showProgress(true);
        mLoginInteractor.login(email, password, this);
    }

    @Override
    public void onEmailError(String msg) {
        mLoginView.showProgress(false);
        mLoginView.setEmailError(msg);

    }

    @Override
    public void onPasswordError(String msg) {
        mLoginView.showProgress(false);
        mLoginView.setPasswordError(msg);
    }

    @Override
    public void onNetworkConnectFailed() {
        mLoginView.showProgress(false);
        mLoginView.showNetworkError();
    }

    @Override
    public void onBeUserResolvableError(int errorCode) {
        mLoginView.showProgress(false);
        mLoginView.showGooglePlayServicesDialog(errorCode);
    }

    @Override
    public void onGooglePlayServicesFailed() {
        mLoginView.showGooglePlayServicesError();
    }

    @Override
    public void onAuthFailed(String msg) {
        mLoginView.showProgress(false);
        mLoginView.showLoginError(msg);
    }

    @Override
    public void onAuthSuccess() {

        //mLoginView.showPushNotifications();
        mLoginView.showCalendario();

    }
}
