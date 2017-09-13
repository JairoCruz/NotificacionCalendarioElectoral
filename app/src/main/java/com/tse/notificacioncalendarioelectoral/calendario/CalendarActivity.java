package com.tse.notificacioncalendarioelectoral.calendario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.tse.notificacioncalendarioelectoral.MainActivity;
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


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.salir:
                showLogin();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    public void showLogin() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }*/

}
