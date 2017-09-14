package com.tse.notificacioncalendarioelectoral.calendario;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.tse.notificacioncalendarioelectoral.MainActivity;
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
        setHasOptionsMenu(true);
        return viewFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_opciones, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.salir:
                showLogin();
                break;
            case R.id.menu_filter:
                showFilterPopUpMenu();
                break;
        }

        return true;
    }

    @Override
    public void showFilterPopUpMenu() {

        PopupMenu popupMenu = new PopupMenu(getContext(), getActivity().findViewById(R.id.menu_filter));
        popupMenu.getMenuInflater().inflate(R.menu.filter_calendario, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.TODOS:
                        calendarContractPresenter.filterEventCalendar("TODOS");
                        break;
                    case R.id.EJECUCION:
                        calendarContractPresenter.filterEventCalendar("EJECUCIÓN");
                        break;
                    case  R.id.FINALIZADA:
                        calendarContractPresenter.filterEventCalendar("FINALIZADA");
                        break;
                }
                return true;
            }
        });
        popupMenu.show();

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
    public void showEventCalendar(Query reference) {

        calendarioAdapter = new CalendarioAdapter(Calendario.class, R.layout.card_item_calendendar,CalendarioAdapter.CalendarioViewHolder.class, reference);
        recyclerViewCalendar.setAdapter(calendarioAdapter);




    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        calendarioAdapter.cleanup();
    }

    public void showLogin() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}
