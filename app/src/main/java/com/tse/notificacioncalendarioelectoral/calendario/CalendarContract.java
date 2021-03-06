package com.tse.notificacioncalendarioelectoral.calendario;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.tse.notificacioncalendarioelectoral.BasePresenter;
import com.tse.notificacioncalendarioelectoral.BaseView;
import com.tse.notificacioncalendarioelectoral.data.Actividad;

import java.util.List;

/**
 * Created by TSE on 7/9/2017.
 */

public interface CalendarContract {

    interface View extends BaseView<Presenter>{

        void showEventCalendar(Query reference, String filtro);

        void showFilterPopUpMenu();

        void showLoadingView(boolean show);


    }



    interface Presenter extends BasePresenter{



        void loadEventCalendar();

        void filterEventCalendar(String filtro);

        void callLoadingView(boolean show);

    }
}
