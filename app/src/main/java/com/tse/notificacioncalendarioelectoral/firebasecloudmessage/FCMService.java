package com.tse.notificacioncalendarioelectoral.firebasecloudmessage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.notification.PushNotificationActivity;
import com.tse.notificacioncalendarioelectoral.notification.PushNotificationFragment;

import java.util.Map;

public class FCMService extends FirebaseMessagingService {

    private static final String TAG = FCMService.class.getSimpleName();


    public FCMService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i(TAG, "Mensaje recibido");
        displayNotification(remoteMessage.getNotification(), remoteMessage.getData());
        sendNewPromoBroadcast(remoteMessage);

    }

    private void sendNewPromoBroadcast(RemoteMessage remoteMessage){
        Intent intent = new Intent(PushNotificationFragment.ACTION_NOTIFY_NEW_PROMO);
        intent.putExtra("title", remoteMessage.getNotification().getTitle());
        intent.putExtra("description", remoteMessage.getNotification().getBody());
        intent.putExtra("expiry_date", remoteMessage.getData().get("expiry_date"));

        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    private void displayNotification(RemoteMessage.Notification notification, Map<String, String> data){

        Intent intent = new Intent(this, PushNotificationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_none_white_24dp)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(Notification.PRIORITY_MAX)
                .setCategory(Notification.CATEGORY_EVENT)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Responsables: " + data.get("responsables_1") + " " + data.get("responsables_2") + " " + data.get("responsables_3") ))
                .setContentIntent(pendingIntent);

       /*
       Set up InboxStyle notification
       NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = {"Queso", "Papas", "Helado", "Yogurt", "Vainilla","Soda"};
        inboxStyle.setBigContentTitle("Event count tracker: ");

        for (int i=0; i < events.length; i++){
            inboxStyle.addLine(events[i]);
            Log.e(TAG, "VALOR: " + events[i]);
        }

        notificationBuilder.setStyle(inboxStyle);*/

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());

    }

}
