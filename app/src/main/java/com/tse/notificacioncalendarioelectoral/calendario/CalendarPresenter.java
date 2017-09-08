package com.tse.notificacioncalendarioelectoral.calendario;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tse.notificacioncalendarioelectoral.data.Calendario;

/**
 * Created by TSE on 7/9/2017.
 */

public class CalendarPresenter implements CalendarContract.Presenter {


    private final CalendarContract.View calendarContractView;


    public CalendarPresenter(CalendarContract.View calendarContractView) {
        this.calendarContractView = calendarContractView;
        calendarContractView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadEventCalendar() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        calendarContractView.showEventCalendar(myRef);
       /* myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot msgSnapshot: dataSnapshot.getChildren()){
                    Calendario calendario = msgSnapshot.getValue(Calendario.class);
                    Log.e("Datos", calendario.getACTIVIDAD() + " " + calendario.getFECHA_INICIO());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", "Fallo por esto:" + databaseError.getMessage());
            }
        });*/

    }
}
