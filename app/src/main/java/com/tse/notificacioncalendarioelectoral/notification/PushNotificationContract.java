package com.tse.notificacioncalendarioelectoral.notification;

import com.tse.notificacioncalendarioelectoral.BasePresenter;
import com.tse.notificacioncalendarioelectoral.BaseView;
import com.tse.notificacioncalendarioelectoral.data.PushNotification;

import java.util.List;

/**
 * Created by TSE on 31/8/2017.
 */

public interface PushNotificationContract {

    interface View extends BaseView<Presenter>{
        void showNotifications(List<PushNotification> notifications);

        void showNoMessagesView();
    }

    interface Presenter extends BasePresenter {

        void registerAppClient();

        void loadNotifications();
    }
}
