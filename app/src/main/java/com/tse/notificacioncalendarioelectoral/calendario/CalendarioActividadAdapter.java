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

import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.data.Actividad;

import java.util.List;

public class CalendarioActividadAdapter extends RecyclerView.Adapter<CalendarioActividadAdapter.CalendarioActividadViewHolder>  {

    private String TAG_CLASS_ADAPTER = CalendarioActividadAdapter.class.getSimpleName();
    CalendarFragment calendarFragment;
    List<Actividad> actividades;

    public CalendarioActividadAdapter(List<Actividad> _actividades,CalendarFragment calendarFragment) {
        this.calendarFragment = calendarFragment;
        this.actividades = _actividades;
    }

    @Override
    public CalendarioActividadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_calendendar, parent, false);
        return new CalendarioActividadViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(CalendarioActividadViewHolder holder, int position) {
        holder.txtActividad.setText(actividades.get(position).getACTIVIDAD());
        holder.txtEstado.setText(actividades.get(position).getESTADO());
        // Log.e("Dias restantes", " " + actividades.get(position).getDIAS_RESTANTES().get("INICIO"));

        if (actividades.get(position).getESTADO().equalsIgnoreCase("en proceso")) {
            holder.txtEstado.setText(actividades.get(position).getESTADO() + " - " + "finaliza en " + actividades.get(position).getDIAS_RESTANTES().get("FIN") + ((actividades.get(position).getDIAS_RESTANTES().get("FIN") > 1) ? " días" : " día"));
            holder.txtEstado.setTextColor(ContextCompat.getColor(calendarFragment.getContext(), R.color.inprocess));
        } else if (actividades.get(position).getESTADO().equalsIgnoreCase("finalizado")) {
            holder.txtEstado.setText(actividades.get(position).getESTADO());
            holder.txtEstado.setTextColor(ContextCompat.getColor(calendarFragment.getContext(), R.color.end));
        } else if (actividades.get(position).getESTADO().equalsIgnoreCase("Inicia hoy") || actividades.get(position).getESTADO().equalsIgnoreCase("Finaliza hoy")) {
            holder.txtEstado.setText(actividades.get(position).getESTADO());
            holder.txtEstado.setTextColor(ContextCompat.getColor(calendarFragment.getContext(), R.color.end));
        } else {
            holder.txtEstado.setText(actividades.get(position).getESTADO() + " " + actividades.get(position).getDIAS_RESTANTES().get("INICIO") + ((actividades.get(position).getDIAS_RESTANTES().get("INICIO") > 1) ? " días" : " día"));
            holder.txtEstado.setTextColor(Color.GRAY);
        }

    }


    @Override
    public int getItemCount() {
        return actividades.size();
    }

    public static class CalendarioActividadViewHolder extends RecyclerView.ViewHolder{

        private TextView txtActividad;
        private TextView txtFechaFin;
        private TextView txtEstado;

        public CalendarioActividadViewHolder(View itemView, int viewType) {

            super(itemView);
            txtActividad = itemView.findViewById(R.id.txtDescripcionActiviad);
            txtEstado = itemView.findViewById(R.id.txtEstado);

        }
    }

}
