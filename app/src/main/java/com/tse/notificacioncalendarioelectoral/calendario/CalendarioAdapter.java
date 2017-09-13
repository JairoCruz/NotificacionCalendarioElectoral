package com.tse.notificacioncalendarioelectoral.calendario;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.data.Calendario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        viewHolder.txtFechaInicio.setText(model.getINICIO());
        viewHolder.txtFechaFin.setText(model.getFIN());
        Log.e("msg",model.getESTADO());
        if (model.getESTADO().equalsIgnoreCase("EJECUCIÓN")){
            viewHolder.txtEstado.setText("EN " + model.getESTADO());
            viewHolder.txtEstado.setTextColor(Color.GREEN);
        }else if (model.getESTADO().equalsIgnoreCase("FINALIZADA")){
            viewHolder.txtEstado.setText(model.getESTADO());
            viewHolder.txtEstado.setTextColor(Color.RED);
        }else {
            viewHolder.txtEstado.setText(model.getESTADO());
            viewHolder.txtEstado.setTextColor(Color.GRAY);
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

    public boolean isActive(String Inicio, String Fin)throws ParseException{
        boolean active = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicio = dateFormat.parse(Inicio);
        Date fechaFin = dateFormat.parse(Fin);
        Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();
        if ((currentDate.getTime() >= fechaInicio.getTime()) && (currentDate.getTime() <= fechaFin.getTime()) ){
            active = true;
        }

        return active;
    }

    public int fechas(String inicio, String fin) throws ParseException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicio = dateFormat.parse(inicio);
        Date fechaFin = dateFormat.parse(fin);
        Date currentDate = Calendar.getInstance(TimeZone.getDefault()).getTime();


        //int dias = ((int) ((fechaInicio.getTime() - currentDate.getTime()) / 86400000));
        int dias;

        if ((currentDate.getTime() > fechaInicio.getTime()) && (currentDate.getTime() < fechaFin.getTime()) ){
             dias = 0;
        }else {
             dias = (int) ((fechaInicio.getTime() - currentDate.getTime()) / 86400000);
        }

        return dias;

    }

}
