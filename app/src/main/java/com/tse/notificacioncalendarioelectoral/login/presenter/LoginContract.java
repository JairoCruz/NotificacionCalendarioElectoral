package com.tse.notificacioncalendarioelectoral.login.presenter;

import com.tse.notificacioncalendarioelectoral.BasePresenter;
import com.tse.notificacioncalendarioelectoral.BaseView;

/**
 * Created by TSE on 30/8/2017.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void showProgress(boolean show);


        void setEmailError(String error);

        void setPasswordError(String error);

        void showLoginError(String msg);

        void showPushNotifications();

        void showGooglePlayServicesDialog(int errorCode);

        void showGooglePlayServicesError();

        void showNetworkError();

    }


    interface Presenter extends BasePresenter {
        void attemptLogin(String email, String password);
    }
}
