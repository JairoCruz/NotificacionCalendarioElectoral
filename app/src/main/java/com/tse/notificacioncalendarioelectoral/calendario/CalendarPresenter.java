package com.tse.notificacioncalendarioelectoral.calendario;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by TSE on 7/9/2017.
 */

public class CalendarPresenter implements CalendarContract.Presenter {



    private final CalendarContract.View calendarContractView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();



    public CalendarPresenter(CalendarContract.View calendarContractView) {
        this.calendarContractView = calendarContractView;
        calendarContractView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loadEventCalendar() {



       calendarContractView.showEventCalendar(myRef.child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO").startAt(1475452800).endAt(1543190400));
       // calendarContractView.showEventCalendar(myRef.child("CALENDARIO").orderByChild("INICIO_AÑO"));
        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot msgSnapshot: dataSnapshot.getChildren()){
                    Actividad calendario = msgSnapshot.getValue(Actividad.class);
                    Log.e("Datos", calendario.getACTIVIDAD() + " " + calendario.getESTADO());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", "Fallo por esto:" + databaseError.getMessage());
            }
        });*/




    }

    @Override
    public void filterEventCalendar(String filtro) {
        switch (filtro){
            case "TODOS":
                Log.e("todas", filtro);
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO"));
                break;
            case "EJECUCIÓN":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").orderByChild("ESTADO").equalTo("EJECUCIÓN"));
                break;
            case "FINALIZADA":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").orderByChild("ESTADO").equalTo("FINALIZADA"));
                break;
            case "LIMPIAR_FILTRO":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").orderByChild("TIMESTAMP_INICIO").startAt(1514851200).endAt(1543190400));
                break;

        }
    }


}
