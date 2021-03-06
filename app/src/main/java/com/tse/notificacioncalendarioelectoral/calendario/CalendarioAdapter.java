package com.tse.notificacioncalendarioelectoral.calendario;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//import com.firebase.ui.common.ChangeEventType;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.github.vipulasri.timelineview.TimelineView;
import com.google.firebase.database.DataSnapshot;
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

//public class CalendarioAdapter extends FirebaseRecyclerAdapter<Actividad, CalendarioAdapter.CalendarioViewHolder> {
public class CalendarioAdapter{

//    private String TAG_CLASS_CALENDARADAPTER = CalendarioAdapter.class.getSimpleName();
//    FirebaseRecyclerOptions<Actividad> options;
//    CalendarFragment calendarFragment;
//
//
//    /**
//     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
//     * {@link FirebaseRecyclerOptions} for configuration options.
//     *
//     * @param options
//     * @param calendarFragment
//     */
//    public CalendarioAdapter(@NonNull FirebaseRecyclerOptions<Actividad> options, CalendarFragment calendarFragment) {
//        super(options);
//        this.options = options;
//        this.calendarFragment = calendarFragment;
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull CalendarioViewHolder holder, int position, @NonNull Actividad model) {
//
//        if(model == null){
//
//        }else {
//
//
//            holder.txtActividad.setText(model.getACTIVIDAD());
//            holder.txtEstado.setText(model.getESTADO());
//            // Log.e("Dias restantes", " " + model.getDIAS_RESTANTES().get("INICIO"));
//
//            if (model.getESTADO().equalsIgnoreCase("en proceso")) {
//                holder.txtEstado.setText(model.getESTADO() + " - " + "finaliza en " + model.getDIAS_RESTANTES().get("FIN") + ((model.getDIAS_RESTANTES().get("FIN") > 1) ? " días" : " día"));
//                holder.txtEstado.setTextColor(ContextCompat.getColor(calendarFragment.getContext(), R.color.inprocess));
//            } else if (model.getESTADO().equalsIgnoreCase("finalizado")) {
//                holder.txtEstado.setText(model.getESTADO());
//                holder.txtEstado.setTextColor(ContextCompat.getColor(calendarFragment.getContext(), R.color.end));
//            } else if (model.getESTADO().equalsIgnoreCase("Inicia hoy") || model.getESTADO().equalsIgnoreCase("Finaliza hoy")) {
//                holder.txtEstado.setText(model.getESTADO());
//                holder.txtEstado.setTextColor(ContextCompat.getColor(calendarFragment.getContext(), R.color.end));
//            } else {
//                holder.txtEstado.setText(model.getESTADO() + " " + model.getDIAS_RESTANTES().get("INICIO") + ((model.getDIAS_RESTANTES().get("INICIO") > 1) ? " días" : " día"));
//                holder.txtEstado.setTextColor(Color.GRAY);
//            }
//            //holder.mTimelineView.setMarkerColor(ContextCompat.getColor(calendarFragment.getContext(), R.color.colorAccent));
//        }
//
//    }
//
//
//    @Override
//    public CalendarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_calendendar, parent, false);
//        return new CalendarioViewHolder(view, viewType);
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return TimelineView.getTimeLineViewType(position,getItemCount());
//    }
//
//    @Override
//    public int getItemCount() {
//      // return super.getItemCount();
//        return options.getSnapshots().size();
//    }
//
//    @Override
//    public void onDataChanged() {
//        //calendarFragment.showLoadingView(false);
//       Log.e("Cambia una sola cosa","cambio");
//       //calendarFragment.crossfade();
//
//    }
//
//    @Override
//    public void onChildChanged(@NonNull ChangeEventType type, @NonNull DataSnapshot snapshot, int newIndex, int oldIndex) {
//        super.onChildChanged(type, snapshot, newIndex, oldIndex);
//       // Log.e("change ", "Valor " + type.name()  + " " + snapshot.getKey() + " "  + newIndex + " " + oldIndex );
//
//    }
//
//    @Override
//    public void onError(@NonNull DatabaseError error) {
//        super.onError(error);
//        Log.e(TAG_CLASS_CALENDARADAPTER, error.getMessage());
//    }
//
//
//
//    public static class CalendarioViewHolder extends RecyclerView.ViewHolder{
//
//        private TextView txtActividad;
//        private TextView txtFechaFin;
//        private TextView txtEstado;
//
//        public CalendarioViewHolder(View itemView, int viewType) {
//
//            super(itemView);
//            txtActividad = itemView.findViewById(R.id.txtDescripcionActiviad);
//            txtEstado = itemView.findViewById(R.id.txtEstado);
//
//        }
//    }
//
//
//    public void convertir(long timestamp){
//        Timestamp stamp = new Timestamp(timestamp);
//        Date date = new Date(stamp.getTime() * 1000);
//        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
//        Calendar c = Calendar.getInstance();
//        long days = TimeUnit.MILLISECONDS.toDays(c.getTimeInMillis());
//        f.setTimeZone(TimeZone.getTimeZone("GMT"));
//
//       /* new CountDownTimer(30000,1000){
//
//            @Override
//            public void onTick(long l) {
//                Log.e("tiempo", "restante "  + l / 1000);
//            }
//
//            @Override
//            public void onFinish() {
//                Log.e("tiempo final","ups se acabo");
//            }
//        }.start();*/
//
//       // Log.e("calendario adapter: ", "times " + f.format(date) + " + ");
//    }

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
