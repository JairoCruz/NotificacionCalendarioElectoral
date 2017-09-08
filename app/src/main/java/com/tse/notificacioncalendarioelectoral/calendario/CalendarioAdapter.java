package com.tse.notificacioncalendarioelectoral.calendario;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.data.Calendario;

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
//        viewHolder.txtFechaInicio.setText(model.getFECHA_INICIO());

    }

    public static class CalendarioViewHolder extends RecyclerView.ViewHolder{

        private TextView txtActividad;
        private TextView txtFechaInicio;

        public CalendarioViewHolder(View itemView) {

            super(itemView);
            txtActividad = (TextView) itemView.findViewById(R.id.txtDescripcionActiviad);
            //txtFechaInicio = (TextView) itemView.findViewById(R.id.txtFechaInicio);

        }
    }
}
