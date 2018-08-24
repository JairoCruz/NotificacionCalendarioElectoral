package com.tse.notificacioncalendarioelectoral.calendario;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.utils.Utils;

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
    public void callLoadingView(boolean show) {
        calendarContractView.showLoadingView(show);
    }

    @Override
    public void loadDecorationCalendar() {
        calendarContractView.showDecorationCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"), "todos");
    }

    @Override
    public void loadEventCalendar() {

        // .startAt(1509494400).endAt(1563148800)


       calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"));
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
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"));
                calendarContractView.showDecorationCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"), "todos");
                callLoadingView(true);
                break;
            case "EN PROCESO":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"));
                calendarContractView.showDecorationCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("ESTADO").equalTo("En proceso"), "En proceso");
                callLoadingView(true);
                break;
            case "FINALIZADO":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("ESTADO").equalTo("Finalizado"));
                calendarContractView.showDecorationCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("ESTADO").equalTo("Finalizado"), "Finalizado");
                callLoadingView(true);
                break;
            case "INICIAN HOY":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("ESTADO").equalTo("Inicia hoy"));
                calendarContractView.showDecorationCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("ESTADO").equalTo("Inicia hoy"), "Inicia hoy");
                callLoadingView(true);
                break;
            case "FINALIZAN HOY":
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("ESTADO").equalTo("Finalizan hoy"));
                calendarContractView.showDecorationCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("ESTADO").equalTo("Finalizan hoy"), "Finalizan hoy");
                callLoadingView(true);
                break;
            case "PROXIMAS":
                calendarContractView.showDecorationCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO"), "Inicia en");
                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("ESTADO").equalTo("Inicia en"));

                callLoadingView(true);
                break;
//            case "LIMPIAR_FILTRO":
//                calendarContractView.showEventCalendar(myRef.child("CALENDARIO").orderByChild("TIMESTAMP_INICIO").startAt(1514851200).endAt(1543190400));
//                break;

        }
    }


}
