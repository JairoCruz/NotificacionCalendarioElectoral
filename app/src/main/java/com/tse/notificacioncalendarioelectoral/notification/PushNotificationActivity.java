package com.tse.notificacioncalendarioelectoral.notification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tse.notificacioncalendarioelectoral.MainActivity;
import com.tse.notificacioncalendarioelectoral.R;

public class PushNotificationActivity extends AppCompatActivity {

    private static final String TAG = PushNotificationActivity.class.getSimpleName();

    private PushNotificationFragment mNotificationFragment;
    private PushNotificationPresenter mNotificationPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        // Existe usuario logeado
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        String fcmToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "FCMTOKEN: " + fcmToken);

        mNotificationFragment = (PushNotificationFragment) getSupportFragmentManager().findFragmentById(R.id.notifications_container);

        if (mNotificationFragment == null) {
            mNotificationFragment = PushNotificationFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.notifications_container, mNotificationFragment).commit();

        }

        mNotificationPresenter = new PushNotificationPresenter(mNotificationFragment, FirebaseMessaging.getInstance());
    }
}
