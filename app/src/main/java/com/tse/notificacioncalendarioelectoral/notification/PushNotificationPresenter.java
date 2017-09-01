package com.tse.notificacioncalendarioelectoral.notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.tse.notificacioncalendarioelectoral.data.PushNotification;
import com.tse.notificacioncalendarioelectoral.data.PushNotificationRepository;

import java.util.ArrayList;

/**
 * Created by TSE on 31/8/2017.
 */

public class PushNotificationPresenter implements PushNotificationContract.Presenter {

    private final PushNotificationContract.View mNotificationView;
    private final FirebaseMessaging mFCMInteractor;


    public PushNotificationPresenter(PushNotificationContract.View notificationView, FirebaseMessaging fcmInteractor){
        mNotificationView = notificationView;
        mFCMInteractor = fcmInteractor;

        notificationView.setPresenter(this);
    }



    @Override
    public void start() {

        registerAppClient();
        loadNotifications();

    }

    @Override
    public void registerAppClient() {

        mFCMInteractor.subscribeToTopic("calendario");

    }

    @Override
    public void loadNotifications() {

        PushNotificationRepository.getInstance().getPushNotifications(new PushNotificationRepository.LoadCallback(){

            @Override
            public void onLoaded(ArrayList<PushNotification> notifications) {
                if (notifications.size() > 0 ) {
                    mNotificationView.showNoMessagesView(false);
                    mNotificationView.showNotifications(notifications);
                }else{
                    mNotificationView.showNoMessagesView(true);
                }
            }
        });

    }

    @Override
    public void savePushMessage(String title, String description, String expiryDate) {
        PushNotification pushMessage = new PushNotification();
        pushMessage.setmTitle(title);
        pushMessage.setmDescription(description);
        pushMessage.setmExpiryDate(expiryDate);

        PushNotificationRepository.getInstance().savePushNotification(pushMessage);

        mNotificationView.showNoMessagesView(false);
        mNotificationView.popPushNotification(pushMessage);
    }
}
