package com.tse.notificacioncalendarioelectoral.calendario;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.data.Actividad;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by TSE on 8/9/2017.
 */

public class CalendarioAdapter extends FirebaseRecyclerAdapter<Actividad, CalendarioAdapter.CalendarioViewHolder> {


    private String TAG_CLASS_CALENDARADAPTER = CalendarioAdapter.class.getSimpleName();
    FirebaseRecyclerOptions<Actividad> options;
    CalendarFragment calendarFragment;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     * @param calendarFragment
     */
    public CalendarioAdapter(@NonNull FirebaseRecyclerOptions<Actividad> options, CalendarFragment calendarFragment) {
        super(options);
        this.options = options;
        this.calendarFragment = calendarFragment;
    }

    @Override
    protected void onBindViewHolder(@NonNull CalendarioViewHolder holder, int position, @NonNull Actividad model) {

        holder.txtActividad.setText(model.getACTIVIDAD());
       // holder.txtDia.setText(String.format("%02d",model.getINICIO_DIA()));
        //holder.txtFechaFin.setText(model.getFIN());
       // holder.txtEstado.setText("eee" + model.getTIMESTAMP_INICIO());
        convertir(model.getTIMESTAMP_INICIO());
       /* if (model.getESTADO().equalsIgnoreCase("EJECUCIÓN")){
            holder.txtEstado.setText("EN milesegundos " + model.getTIMESTAMP_INICIO());
            convertir(model.getTIMESTAMP_INICIO());
            holder.txtEstado.setTextColor(Color.GREEN);
        }else if (model.getESTADO().equalsIgnoreCase("FINALIZADA")){
            holder.txtEstado.setText(model.getESTADO());
            holder.txtEstado.setTextColor(Color.RED);
        }else {
            holder.txtEstado.setText(model.getESTADO());
            holder.txtEstado.setTextColor(Color.GRAY);
        }*/
       //holder.mTimelineView.setMarkerColor(ContextCompat.getColor(calendarFragment.getContext(), R.color.colorAccent));

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
    public int getItemCount() {
      // return super.getItemCount();
        return options.getSnapshots().size();
    }

    @Override
    public void onDataChanged() {
        calendarFragment.loading(false);
    }

    @Override
    public void onError(@NonNull DatabaseError error) {
        super.onError(error);
        Log.e(TAG_CLASS_CALENDARADAPTER, error.getMessage());
    }



    public static class CalendarioViewHolder extends RecyclerView.ViewHolder{

        private TextView txtActividad;
       // private TextView txtDia;
        private TextView txtFechaFin;
        private TextView txtEstado;
       // private TimelineView mTimelineView;

        public CalendarioViewHolder(View itemView, int viewType) {

            super(itemView);
            txtActividad = itemView.findViewById(R.id.txtDescripcionActiviad);
            //txtDia = itemView.findViewById(R.id.textDia);
            //txtFechaFin = itemView.findViewById(R.id.txtFechaFin);
           // txtEstado = itemView.findViewById(R.id.txtEstado);
           // mTimelineView = itemView.findViewById(R.id.time_marker);
           // mTimelineView.initLine(viewType);

        }
    }


    public void convertir(long timestamp){
        Timestamp stamp = new Timestamp(timestamp);
        Date date = new Date(stamp.getTime() * 1000);
        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        long days = TimeUnit.MILLISECONDS.toDays(c.getTimeInMillis());
        f.setTimeZone(TimeZone.getTimeZone("GMT"));

       /* new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
                Log.e("tiempo", "restante "  + l / 1000);
            }

            @Override
            public void onFinish() {
                Log.e("tiempo final","ups se acabo");
            }
        }.start();*/

       // Log.e("calendario adapter: ", "times " + f.format(date) + " + ");
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
