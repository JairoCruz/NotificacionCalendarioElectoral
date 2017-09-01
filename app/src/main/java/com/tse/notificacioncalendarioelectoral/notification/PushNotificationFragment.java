package com.tse.notificacioncalendarioelectoral.notification;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.data.PushNotification;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PushNotificationFragment extends Fragment implements PushNotificationContract.View {


    public static final String ACTION_NOTIFY_NEW_PROMO = "NOTIFY_NEW_PROMO";

    private RecyclerView mRecyclerView;
    private LinearLayout mNoMessagesView;
    private PushNotificationAdapter mNotificationsAdapter;

    private PushNotificationPresenter mPresenter;

    private BroadcastReceiver mNotificationReceiver;

    public PushNotificationFragment() {
        // Required empty public constructor
    }

    public static PushNotificationFragment newInstance(){
        PushNotificationFragment fragment = new PushNotificationFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            // obtener argumentos si los hubiere
        }

        mNotificationReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String title = intent.getStringExtra("title");
                String descripcion = intent.getStringExtra("description");
                String expiryDate = intent.getStringExtra("expiry_date");
                mPresenter.savePushMessage(title, descripcion, expiryDate);
            }
        };

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification, container, false);

        mNotificationsAdapter = new PushNotificationAdapter();
        mRecyclerView = (RecyclerView) root.findViewById(R.id.rv_notifications_list);
        mNoMessagesView = (LinearLayout) root.findViewById(R.id.noMessages);
        mRecyclerView.setAdapter(mNotificationsAdapter);
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mNotificationReceiver, new IntentFilter(ACTION_NOTIFY_NEW_PROMO));
    }

    @Override
    public void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mNotificationReceiver);

    }

    @Override
    public void setPresenter(PushNotificationContract.Presenter presenter) {
        if (presenter != null){
            mPresenter = (PushNotificationPresenter) presenter;
        } else {
            throw new RuntimeException("El presenter de notificaciones no puede ser null");
        }
    }

    @Override
    public void showNotifications(ArrayList<PushNotification> notifications) {
        mNotificationsAdapter.replaceData(notifications);
    }

    @Override
    public void showNoMessagesView(boolean empty) {
        mRecyclerView.setVisibility(empty ? View.GONE : View.VISIBLE);
        mNoMessagesView.setVisibility(empty ? View.VISIBLE : View.GONE);
    }

    @Override
    public void popPushNotification(PushNotification pushMessage) {
        mNotificationsAdapter.addItem(pushMessage);
    }
}
