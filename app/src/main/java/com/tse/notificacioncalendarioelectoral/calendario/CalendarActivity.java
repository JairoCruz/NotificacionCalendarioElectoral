package com.tse.notificacioncalendarioelectoral.calendario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.tse.notificacioncalendarioelectoral.R;

public class CalendarActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Set Up Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        CalendarFragment calendarFragment = (CalendarFragment) getSupportFragmentManager().findFragmentById(R.id.layoutFragmentCalendar);
        // add fragment to CalendarActivity
        if (calendarFragment == null){
            calendarFragment = CalendarFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.layoutFragmentCalendar, calendarFragment).commit();
        }


        new CalendarPresenter(calendarFragment);
    }
}
