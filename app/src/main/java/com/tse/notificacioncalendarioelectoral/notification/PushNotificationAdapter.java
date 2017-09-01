package com.tse.notificacioncalendarioelectoral.notification;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.data.PushNotification;

import java.util.ArrayList;

/**
 * Created by TSE on 31/8/2017.
 */

public class PushNotificationAdapter extends RecyclerView.Adapter<PushNotificationAdapter.ViewHolder> {


    ArrayList<PushNotification> pushNotifications = new ArrayList<>();

    public PushNotificationAdapter(){

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_list_notification, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PushNotification notification = pushNotifications.get(position);
        holder.title.setText(notification.getmTitle());
        holder.description.setText(notification.getmDescription());
        holder.expiryDate.setText(String.format("Valido hasta el %s", notification.getmExpiryDate()));


    }

    @Override
    public int getItemCount() {
        return pushNotifications.size();
    }

    public void replaceData(ArrayList<PushNotification> items){
        setList(items);
        notifyDataSetChanged();
    }

    public void setList(ArrayList<PushNotification> list){
        this.pushNotifications = list;
    }

    public void addItem(PushNotification pushMessage){
        pushNotifications.add(0, pushMessage);
        notifyItemInserted(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView description;
        public TextView expiryDate;


        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_title);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            expiryDate = (TextView) itemView.findViewById(R.id.tv_expiry_date);
        }
    }
}
