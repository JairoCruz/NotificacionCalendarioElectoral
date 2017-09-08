package com.tse.notificacioncalendarioelectoral.calendario;

import com.google.firebase.database.DatabaseReference;
import com.tse.notificacioncalendarioelectoral.BasePresenter;
import com.tse.notificacioncalendarioelectoral.BaseView;

/**
 * Created by TSE on 7/9/2017.
 */

public interface CalendarContract {

    interface View extends BaseView<Presenter>{

        void showEventCalendar(DatabaseReference reference);

    }



    interface Presenter extends BasePresenter{

        void loadEventCalendar();

    }
}
