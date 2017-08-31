package com.tse.notificacioncalendarioelectoral.data;

import java.util.UUID;

/**
 * Created by TSE on 31/8/2017.
 */

public class PushNotification {

    private String id;
    private String mTitle;
    private String mDescription;
    private String mExpiryDate;

    public PushNotification(){
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmExpiryDate() {
        return mExpiryDate;
    }

    public void setmExpiryDate(String mExpiryDate) {
        this.mExpiryDate = mExpiryDate;
    }
}
