package com.tse.notificacioncalendarioelectoral;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.tse.notificacioncalendarioelectoral.login.presenter.LoginContract;
import com.tse.notificacioncalendarioelectoral.login.presenter.LoginInteractor;
import com.tse.notificacioncalendarioelectoral.login.presenter.LoginPresenter;

public class MainActivity extends AppCompatActivity implements fragment_login.Callback {

    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment_login loginFragment = (fragment_login) getSupportFragmentManager().findFragmentById(R.id.login_container);
        // add fragment to main activity
        if (loginFragment == null){
            loginFragment = fragment_login.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.login_container, loginFragment).commit();

        }

        mFirebaseAuth = FirebaseAuth.getInstance();

        LoginInteractor loginInteractor = new LoginInteractor(getApplicationContext(), mFirebaseAuth);
        new LoginPresenter(loginFragment, loginInteractor);
    }

    @Override
    public void onInvokeGooglePlayServices(int codeError) {
        showPlayServicesErrorDialog(codeError);
    }

    void showPlayServicesErrorDialog(final int errorCode){
        Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, errorCode, REQUEST_GOOGLE_PLAY_SERVICES);
        dialog.show();
    }

}
