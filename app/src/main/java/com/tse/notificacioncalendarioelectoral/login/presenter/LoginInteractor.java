package com.tse.notificacioncalendarioelectoral.login.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;


/**
 * Created by TSE on 31/8/2017.
 */

public class LoginInteractor {

    private final Context mContex;
    private FirebaseAuth mFirebaseAuth;

    public LoginInteractor(Context context, FirebaseAuth firebaseAuth){
        mContex = context;
        if (firebaseAuth != null){
            mFirebaseAuth = firebaseAuth;
        }else {
            throw new RuntimeException("La instancia de FirebaseAuth no puede ser null");
        }



    }

    public void login(String email, String password, final Callback callback){
        boolean c1 = isValidEmail(email, callback);
        boolean c2 = isValidPassword(password, callback);

        if (!(c1 && c2)){
            return;
        }

        if (!isNetworkAvailable()){
            callback.onNetworkConnectFailed();
            return;
        }

        if (!isGooglePlayServicesAvailable(callback)){
            return;
        }

        signInUser(email, password, callback);
    }


    private boolean isValidPassword(String password, Callback callback){
        boolean isValid = true;
        if (TextUtils.isEmpty(password)){
            callback.onPasswordError("La contraseña no puede estar vacia");
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidEmail(String email, Callback callback){
        boolean isValid = true;
        if (TextUtils.isEmpty(email)){
            callback.onEmailError("El email no puede estar vacio");
            isValid = false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            callback.onEmailError("El email es invalido");
            isValid = false;
        }
        return isValid;
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) mContex.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private boolean isGooglePlayServicesAvailable(Callback callback){
       int statusCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(mContex);

       if (GoogleApiAvailability.getInstance().isUserResolvableError(statusCode)){
         callback.onBeUserResolvableError(statusCode);
         return false;
       }else if(statusCode != ConnectionResult.SUCCESS){
           callback.onGooglePlayServicesFailed();
           return false;

       }
       return true;
    }

    private void signInUser(String email, String password, final Callback callback){

        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    callback.onAuthFailed(task.getException().getMessage());
                }else{
                    callback.onAuthSuccess();
                }
            }
        });

    }


    interface Callback {
        void onEmailError(String msg);

        void onPasswordError(String msg);

        void onNetworkConnectFailed();

        void onBeUserResolvableError(int errorCode);

        void onGooglePlayServicesFailed();

        void onAuthFailed(String msg);

        void onAuthSuccess();
    }
}
