package com.tse.notificacioncalendarioelectoral.calendario;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tse.notificacioncalendarioelectoral.MainActivity;
import com.tse.notificacioncalendarioelectoral.R;
import com.tse.notificacioncalendarioelectoral.data.Actividad;
import com.tse.notificacioncalendarioelectoral.utils.Utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment implements CalendarContract.View{


    private CalendarContract.Presenter calendarContractPresenter;
    private CalendarioAdapter calendarioAdapter;
    private int mShortAnimationDuration;

    RecyclerView recyclerViewCalendar;
    ProgressBar progressBar;


    public CalendarFragment() {
        // Required empty public constructor
    }

    public static CalendarFragment newInstance(){
        CalendarFragment calendarFragment = new CalendarFragment();
        return calendarFragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        showPro();


    }

    @Override
    public void onPause() {
        super.onPause();
        recyclerViewCalendar.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        //showPro();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View viewFragment = inflater.inflate(R.layout.fragment_calendar, container, false);
        recyclerViewCalendar = viewFragment.findViewById(R.id.recyclerViewCalendario);
        progressBar = viewFragment.findViewById(R.id.progressBar2);


        recyclerViewCalendar.setVisibility(View.GONE);
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);



        // He cambiado el addValueEventListener por addListenerForSingleValueEvent para leer los datos de los encabezados
        // del ItemDecoration una sola vez, ya que con el anterior metodo cada vez que se actualizaban los datos
        // el itemDecoration cambiaba y le agregaba un espacio

        calendarContractPresenter.loadDecorationCalendar();


        recyclerViewCalendar.setLayoutManager(new LinearLayoutManager(getActivity()));

        calendarContractPresenter.loadEventCalendar();

        setHasOptionsMenu(true);



        return viewFragment;
    }

    private List<Actividad> getCalendario(List<Actividad> c){
        Collections.sort(c);
        /*for (int j = 0; j < c.size(); j++){
          // Log.e("Collecion", "fecha: " + c.get(j).getFIN_MES_T());
        }*/
        return c;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

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
                    case R.id.INICIA_HOY:
                        calendarContractPresenter.filterEventCalendar(getString(R.string.nav_inician_hoy));
                        recyclerViewCalendar.setVisibility(View.GONE);
                        break;
                    case R.id.FINALIZA_HOY:
                        calendarContractPresenter.filterEventCalendar(getString(R.string.nav_finalizan_hoy));
                        recyclerViewCalendar.setVisibility(View.GONE);
                        break;
                    case R.id.EN_PROCESO:
                        calendarContractPresenter.filterEventCalendar(getString(R.string.nav_ejecucion));
                        recyclerViewCalendar.setVisibility(View.GONE);
                        break;
                    case R.id.PROXIMAS:
                        calendarContractPresenter.filterEventCalendar(getString(R.string.nav_proximas));
                        recyclerViewCalendar.setVisibility(View.GONE);
                        break;
                    case  R.id.FINALIZADO:
                        calendarContractPresenter.filterEventCalendar(getString(R.string.nav_finalizada));
                        recyclerViewCalendar.setVisibility(View.GONE);
                        break;
                    case R.id.TODOS:
                        calendarContractPresenter.filterEventCalendar(getString(R.string.nav_todos));
                        recyclerViewCalendar.setVisibility(View.GONE);
                        break;
//                    case  R.id.LIMPIAR:
//                        calendarContractPresenter.filterEventCalendar("LIMPIAR_FILTRO");
//                        break;
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

        Context c = getContext();
        FirebaseRecyclerOptions<Actividad> options = new FirebaseRecyclerOptions.Builder<Actividad>()
                .setQuery(reference, Actividad.class)
                // Establecer esta propieda hace que automaticamente se maneje el onStart y onStop en el ciclo de vida del adaptador
                .setLifecycleOwner(this)
                .build();
        calendarioAdapter = new CalendarioAdapter(options, this);
        recyclerViewCalendar.setAdapter(calendarioAdapter);

    }

    @Override
    public void showLoadingView(boolean show){
            if (show){
                progressBar.setVisibility(View.VISIBLE);
            }
            else {
                progressBar.setVisibility(View.GONE);
            }

    }

    @Override
    public void showDecorationCalendar(Query reference, final String filtro) {

       // FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference();
        // myRef.child("CALENDARIO").child(Utils.anio_2019).child("ACTIVIDADES").orderByChild("TIMESTAMP_INICIO")

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Actividad> listActividad = new ArrayList<>();
                Actividad a = new Actividad();

                RecyclerSectionItemDecoration sectionItemDecoration = null;

                for (DataSnapshot msgSnapshot: dataSnapshot.getChildren()){
                    Actividad actividad = msgSnapshot.getValue(Actividad.class);


//                    if (filtro == "En proceso" && actividad.getESTADO().equals("En proceso")){
//                        Log.e("msg", "nada");
//                        a = actividad;
//                        listActividad.add(a);
//                        }
//                        else if(filtro == "Finalizado" && actividad.getESTADO().equals("Finalizado")){
//                        Log.e("msg", "nada de nada");
//                            a = actividad;
//                            listActividad.add(a);
//                    }





                    if (filtro == "En proceso" && actividad.getESTADO().equals(filtro)){
                        listActividad.add(actividad);
                        Log.e("Msg", "En proceso");
                    }
                    else if (filtro ==  "Finalizado" && actividad.getESTADO().equals(filtro)){
                        listActividad.add(actividad);
                        Log.e("Msg", "Finalizado");
                    }else if (filtro ==  "Inicia hoy" && actividad.getESTADO().equals(filtro)){
                        listActividad.add(actividad);
                        Log.e("Msg", "Inicia hoy");
                    }else if (filtro ==  "Finalizan hoy" && actividad.getESTADO().equals(filtro)){
                        listActividad.add(actividad);
                        Log.e("Msg", "Finaliza hoy");
                    }else if(filtro ==  "Inicia en" && actividad.getESTADO().equals(filtro)){
                        recyclerViewCalendar.removeItemDecoration(sectionItemDecoration);
                        listActividad.add(actividad);
                        Log.e("Msg", "Inicia en " + actividad.getINICIO_MES_T() + " " + actividad.getACTIVIDAD());

                    }else if(filtro == "todos"){
                        listActividad.add(actividad);
                        Log.e("Msg", "todos " + actividad.getINICIO_MES_T() + " " + actividad.getESTADO());
                    }
                    //Log.e("Datos secuencia", actividad.getINICIO().subSequence(0,10) + " " + actividad.getESTADO());
                    // Log.e("Fecha desde objeto", actividad.getINICIO_MES_T());
                }

                // Log.e("longitud", " : " + listActividad.get(0).getACTIVIDAD());

                Log.e("tamaño lista", "tamaño " + listActividad.size());

                // =  new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),true, getSectionCallback(getCalendario(listActividad)));
//                if (sectionItemDecoration == null){
//                    sectionItemDecoration = new RecyclerSectionItemDecoration(getResources().getDimensionPixelSize(R.dimen.recycler_section_header_height),true, getSectionCallback(getCalendario(listActividad)));
//                    recyclerViewCalendar.addItemDecoration(sectionItemDecoration);
//                    Log.e("if", "nulo");
//                }else {
//                    Log.e("if", "ni entro");
//
//                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Error", "Fallo por esto:" + databaseError.getMessage());
            }
        });

    }

    private RecyclerSectionItemDecoration.SectionCallback getSectionCallback(final List<Actividad> people) {

        /**
         * Cuando coloque el encabezado por meses, no debo de utilizar en la clase model calendario el metodo comparable y el metodo Collection.sort
         * ya que devuelvo el objeto ordenado segun la fecha, si utilizo ese metodo, me lo arreglara alfabeticamente. por lo tanto no debo utilizar
         * y solo debo colocar en issection y getSeccionheader, tal cual viene de la consulta. esta comprobado que funciona.
         */

        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
               // return position == 0 || people.get(position).getINICIO_MES_T().charAt(0) != people.get(position - 1).getINICIO_MES_T().charAt(0);
              // return position == 0 || !people.get(position).getINICIO_MES_T().equals(people.get(position - 1).getINICIO_MES_T().toString());
                return position == 0 || !convertir(people.get(position).getTIMESTAMP_INICIO()).equals(convertir(people.get(position - 1).getTIMESTAMP_INICIO()));
            }

            @Override
            public String getSectionHeader(int position) {
                //return firstUpper(people.get(position).getINICIO_MES_T().toString());
                return convertir(people.get(position).getTIMESTAMP_INICIO());
            }

           /*
            Este metodo lo utilizo con secuencia de caracteres
           @Override
            public CharSequence getSectionHeader(int position) {
                return people.get(position).getINICIO_MES_T().subSequence(0,6);
            }*/
        };
    }

    public String firstUpper(String string){
        return Character.toUpperCase(string.charAt(0)) + string.substring(1, string.length());
    }

    public String convertir(long timestamp){
        Timestamp stamp = new Timestamp(timestamp);
        Date date = new Date(stamp.getTime() * 1000);
        DateFormat f = new SimpleDateFormat("dd MMMM yyyy", new Locale("es", "ES"));
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

        //Log.e("calendario adapter: ", "times " + f.format(date) + " + ");
        return f.format(date);
    }

    public void showLogin() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }

    public void crossfade() {
        recyclerViewCalendar.setAlpha(0f);
        recyclerViewCalendar.setVisibility(View.VISIBLE);

        recyclerViewCalendar.animate()
                            .alpha(1f)
                            .setDuration(mShortAnimationDuration)
                            .setListener(null);

        progressBar.animate()
                    .alpha(0f)
                    .setDuration(mShortAnimationDuration)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                        }
                    });
    }

    public void showPro(){
        progressBar.setAlpha(0f);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.animate().alpha(1f).setDuration(mShortAnimationDuration).setListener(null);
    }

}
