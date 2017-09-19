package com.tse.notificacioncalendarioelectoral.firebasecloudmessage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.calendario.CalendarActivity;
import com.tse.notificacioncalendarioelectoral.notification.PushNotificationActivity;
import com.tse.notificacioncalendarioelectoral.notification.PushNotificationFragment;

import java.util.Map;

public class FCMService extends FirebaseMessagingService {



    // Segun la documentacion de firebase cuando la app esta en segundo plano, los datos estos datos son los de remoteMessage.getData de la notificacion se pasan al intent de la actividad
    // que esta definida como principal en el androidmanifest.
    // Sin embargo FirebaseMessaginService tambien tiene un metodo que se puede implementar llamado handleIntent el cual funciona cuando la app esta en
    // background y foreground

    private static final String TAG = FCMService.class.getSimpleName();


    public FCMService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.i(TAG, "texto " + remoteMessage.getData());
        //displayNotification(remoteMessage.getNotification(), remoteMessage.getData());
        //sendNewPromoBroadcast(remoteMessage);




    }

    /* Este metodo siempre se ejecuta aunque la aplicacion este en background o foreground
    * por lo tanto utilizare el metodo onMessageReceived y contruire los parametros para poder utilizarlos anque no es necesario ya que podria
    * hacerlo directamente desde este metodo.
    * he comentado el super.handleIntent(intent) ya que si lo dejara sin comentar al estar la aplicacion en background me crearia una notificacion extra
    * aparte de la que yo he personalizado, y es de la que se menciona en la documentacion de firebase cuando la app esta en background, entonces para evitar
    * eso he decidido mejor comentarla y personalizar el comportamiento.
    * */
    @Override
    public void handleIntent(Intent intent) {
       // super.handleIntent(intent);
        Bundle bundle = intent.getExtras();
        RemoteMessage remo = new RemoteMessage.Builder("algo")
                .addData("titulo", bundle.getString("estado"))
                .build();


        onMessageReceived(remo);
        //displayNotificationBackground(bundle);
        //Log.e(FCMService.class.getSimpleName(), bundle.getString("gcm.notification.title"));
       // Log.e(FCMService.class.getSimpleName(), bundle.getString("gcm.notification.body"));


    }

    private void displayNotificationBackground(Bundle extras){
        Intent intentCalendar = new Intent(this, CalendarActivity.class);
        intentCalendar.putExtra("title", extras.getString("title"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intentCalendar, PendingIntent.FLAG_ONE_SHOT);

        String bigT = extras.getString("textoLargo");

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBackground = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_none_white_24dp)
                .setContentTitle(extras.getString("title"))
                .setContentText(extras.getString("bodyText"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(Notification.PRIORITY_MAX)
                .setCategory(Notification.CATEGORY_EVENT)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigT))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBackground.build());
    }

    private void sendNewPromoBroadcast(RemoteMessage remoteMessage){
        Intent intent = new Intent(PushNotificationFragment.ACTION_NOTIFY_NEW_PROMO);
        intent.putExtra("title", remoteMessage.getNotification().getTitle());
        intent.putExtra("description", remoteMessage.getNotification().getBody());
        intent.putExtra("expiry_date", remoteMessage.getData().get("expiry_date"));

        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    private void displayNotification(RemoteMessage.Notification notification, Map<String, String> data){

        Intent intent = new Intent(this, CalendarActivity.class);
        intent.putExtra("texto","hola desde notificacion");
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        String msg = notification.getBody() + " "  + data.get("estado") + ". " + "Los responsables de la actividad son: " + data.get("responsables");
        Log.e(TAG, "VALOR: " + msg);

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
                //.setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentIntent(pendingIntent);

           //Set up InboxStyle notification
           NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            String[] events = {"Queso", "Papas", "Helado", "Yogurt", "Vainilla","Soda"};
            inboxStyle.setBigContentTitle(notification.getTitle());

           for (int i=0; i < events.length; i++){
                inboxStyle.addLine(events[i]);
              //Log.e(TAG, "VALOR: " + data.get("fecha_inicio"));
            }

        notificationBuilder.setStyle(inboxStyle);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());

    }

}
