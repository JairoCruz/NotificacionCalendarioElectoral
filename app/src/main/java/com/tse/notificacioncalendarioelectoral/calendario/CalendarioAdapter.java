package com.tse.notificacioncalendarioelectoral.calendario;

import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.data.Calendario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by TSE on 8/9/2017.
 */

public class CalendarioAdapter extends FirebaseRecyclerAdapter<Calendario, CalendarioAdapter.CalendarioViewHolder> {



    public CalendarioAdapter(Class<Calendario> modelClass, int modelLayout, Class<CalendarioViewHolder> viewHolderClass, Query query) {
        super(modelClass, modelLayout, viewHolderClass, query);
    }

    @Override
    protected void populateViewHolder(CalendarioViewHolder viewHolder, Calendario model, int position) {

        viewHolder.txtActividad.setText(model.getACTIVIDAD());
        viewHolder.txtFechaInicio.setText(model.getFECHA_INICIO());
        viewHolder.txtFechaFin.setText(model.getFECHA_FIN());

        try{
            //fechas(model.getFECHA_INICIO(), model.getFECHA_FIN());
            int d = fechas(model.getFECHA_INICIO(), model.getFECHA_FIN());

            if (d == 0){
                viewHolder.txtEstado.setText("En ejecucion" + d);
                viewHolder.txtEstado.setTextColor(Color.GREEN);
            } else if(d > 0 && d != 0){
                viewHolder.txtEstado.setText("Inicia en: " + Integer.toString(d) + " dias"+ d);
                viewHolder.txtEstado.setTextColor(Color.GRAY);
            } else {
                viewHolder.txtEstado.setText("Finalizada" + d);
                viewHolder.txtEstado.setTextColor(Color.RED);
            }
            //viewHolder.txtEstado.setText(Integer.toString(fechas(model.getFECHA_INICIO(), model.getFECHA_FIN())));

        }catch (Exception e){
            Log.e(CalendarioAdapter.class.getSimpleName(), e.getMessage());
        }


    }

    public static class CalendarioViewHolder extends RecyclerView.ViewHolder{

        private TextView txtActividad;
        private TextView txtFechaInicio;
        private TextView txtFechaFin;
        private TextView txtEstado;

        public CalendarioViewHolder(View itemView) {

            super(itemView);
            txtActividad = (TextView) itemView.findViewById(R.id.txtDescripcionActiviad);
            txtFechaInicio = (TextView) itemView.findViewById(R.id.txtFechaInicio);
            txtFechaFin = (TextView) itemView.findViewById(R.id.txtFechaFin);
            txtEstado = (TextView) itemView.findViewById(R.id.txtEstado);

        }
    }

    public int fechas(String inicio, String fin) throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicio = dateFormat.parse(inicio);
        Date fechaFin = dateFormat.parse(fin);
        Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();

        Log.e("ver", "v  " + dateFormat.format(fechaInicio) +  (currentDate.getTime() > fechaInicio.getTime()));
        Log.e("ver", "fin  " + dateFormat.format(fechaFin) +  (currentDate.getTime() < fechaFin.getTime()));
        Log.e("ver", "algo  " + dateFormat.format(fechaInicio) + ((int) ((fechaInicio.getTime() - currentDate.getTime()) / 86400000)));


        //int dias = ((int) ((fechaInicio.getTime() - currentDate.getTime()) / 86400000));
        int dias;

        if ((currentDate.getTime() > fechaInicio.getTime()) && (currentDate.getTime() < fechaFin.getTime()) ){
             dias = 0;
        }else {
             dias = (int) ((fechaInicio.getTime() - currentDate.getTime()) / 86400000);
        }

        return dias;


        //return dias;
       // Log.e(CalendarioAdapter.class.getSimpleName(), "Hay " + dias);
        //Log.e(CalendarioAdapter.class.getSimpleName(), "Fecha Actual " + dateFormat.format(currentDate));
    }

}
