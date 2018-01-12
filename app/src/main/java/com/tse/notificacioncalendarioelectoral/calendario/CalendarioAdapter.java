package com.tse.notificacioncalendarioelectoral.calendario;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.vipulasri.timelineview.TimelineView;
import com.google.firebase.database.DatabaseError;
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


    private String TAG_CLASS_CALENDARADAPTER = CalendarioAdapter.class.getSimpleName();


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CalendarioAdapter(@NonNull FirebaseRecyclerOptions<Calendario> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CalendarioViewHolder holder, int position, @NonNull Calendario model) {

        holder.txtActividad.setText(model.getACTIVIDAD());
        holder.txtFechaInicio.setText(model.getINICIO());
        holder.txtFechaFin.setText(model.getFIN());

        if (model.getESTADO().equalsIgnoreCase("EJECUCIÓN")){
            holder.txtEstado.setText("EN " + model.getESTADO());
            holder.txtEstado.setTextColor(Color.GREEN);
        }else if (model.getESTADO().equalsIgnoreCase("FINALIZADA")){
            holder.txtEstado.setText(model.getESTADO());
            holder.txtEstado.setTextColor(Color.RED);
        }else {
            holder.txtEstado.setText(model.getESTADO());
            holder.txtEstado.setTextColor(Color.GRAY);
        }

    }

    @Override
    public CalendarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_calendendar, parent, false);
        return new CalendarioViewHolder(view, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }




    @Override
    public void onDataChanged() {
        Log.e(TAG_CLASS_CALENDARADAPTER, "el recycler cambio");
    }

    @Override
    public void onError(@NonNull DatabaseError error) {
        super.onError(error);
        Log.e(TAG_CLASS_CALENDARADAPTER, error.getMessage());
    }



    public static class CalendarioViewHolder extends RecyclerView.ViewHolder{

        private TextView txtActividad;
        private TextView txtFechaInicio;
        private TextView txtFechaFin;
        private TextView txtEstado;
        private TimelineView mTimelineView;

        public CalendarioViewHolder(View itemView, int viewType) {

            super(itemView);
            txtActividad = itemView.findViewById(R.id.txtDescripcionActiviad);
            txtFechaInicio = itemView.findViewById(R.id.txtFechaInicio);
            txtFechaFin = itemView.findViewById(R.id.txtFechaFin);
            txtEstado = itemView.findViewById(R.id.txtEstado);
            mTimelineView = itemView.findViewById(R.id.time_marker);
            mTimelineView.initLine(viewType);

        }
    }

    /*public boolean isActive(String Inicio, String Fin)throws ParseException{
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

    }*/

}
