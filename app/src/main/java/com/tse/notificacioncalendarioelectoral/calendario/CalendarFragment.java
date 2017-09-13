package com.tse.notificacioncalendarioelectoral.calendario;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.data.Calendario;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment implements CalendarContract.View{


    private CalendarContract.Presenter calendarContractPresenter;
    private CalendarioAdapter calendarioAdapter;

    RecyclerView recyclerViewCalendar;


    public CalendarFragment() {
        // Required empty public constructor
    }

    public static CalendarFragment newInstance(){
        CalendarFragment calendarFragment = new CalendarFragment();
        return calendarFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewFragment = inflater.inflate(R.layout.fragment_calendar, container, false);
        recyclerViewCalendar = (RecyclerView) viewFragment.findViewById(R.id.recyclerViewCalendario);
        recyclerViewCalendar.setLayoutManager(new LinearLayoutManager(getActivity()));
        calendarContractPresenter.loadEventCalendar();
        return viewFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //calendarContractPresenter.loadEventCalendar();
    }

    @Override
    public void setPresenter(CalendarContract.Presenter presenter) {

        if (presenter != null){
            calendarContractPresenter = presenter;
        }else {
            throw new RuntimeException("El presenter no puede ser nulo");
        }



    }

    @Override
    public void showEventCalendar(DatabaseReference reference) {

        calendarioAdapter = new CalendarioAdapter(Calendario.class, R.layout.card_item_calendendar,CalendarioAdapter.CalendarioViewHolder.class, reference.orderByChild("FECHA_INICIO").startAt("1/9/2017").endAt("10/9/2017"));
        recyclerViewCalendar.setAdapter(calendarioAdapter);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        calendarioAdapter.cleanup();
    }
}
