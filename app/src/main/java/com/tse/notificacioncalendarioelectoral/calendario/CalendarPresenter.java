package com.tse.notificacioncalendarioelectoral.calendario;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.data.Actividad;
import com.tse.notificacioncalendarioelectoral.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TSE on 7/9/2017.
 */

public class CalendarPresenter implements CalendarContract.Presenter {



    private final CalendarContract.View calendarContractView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    String CALENDARIO_PRESENTER = CalendarPresenter.class.getSimpleName();



    public CalendarPresenter(CalendarContract.View calendarContractView) {
        this.calendarContractView = calendarContractView;
        calendarContractView.setPresenter(this);
    }

    @Override
    public void start() {

    }



    @Override
    public void callLoadingView(boolean show) {
        calendarContractView.showLoadingView(show);
    }


    @Override
    public void loadEventCalendar() {

      calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"), "todas");


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


        // Resources.getSystem().getString(R.string.nav_todos)

    }

    @Override
    public void filterEventCalendar(String filtro) {
        switch (filtro){
            case "TODAS":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"), "todas");
                break;
            case "EN PROCESO":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"), "En proceso");
                break;
            case "FINALIZADO":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"), "Finalizado");
                break;
            case "INICIAN HOY":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"), "Inicia hoy");
                break;
            case "FINALIZAN HOY":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"), "Finaliza hoy");
                break;
            case "PROXIMAS":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"), "Inicia en");
                break;
        }
    }


}
