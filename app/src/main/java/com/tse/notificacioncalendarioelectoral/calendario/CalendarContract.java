package com.tse.notificacioncalendarioelectoral.calendario;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.tse.notificacioncalendarioelectoral.BasePresenter;
import com.tse.notificacioncalendarioelectoral.BaseView;

/**
 * Created by TSE on 7/9/2017.
 */

public interface CalendarContract {

    interface View extends BaseView<Presenter>{

        void showEventCalendar(Query reference);

        void showFilterPopUpMenu();

        void showLoadingView(boolean show);

        void showDecorationCalendar(Query reference, String filtro);

    }



    interface Presenter extends BasePresenter{

        void loadEventCalendar();

        void filterEventCalendar(String filtro);

        void callLoadingView(boolean show);

        void loadDecorationCalendar();

    }
}
