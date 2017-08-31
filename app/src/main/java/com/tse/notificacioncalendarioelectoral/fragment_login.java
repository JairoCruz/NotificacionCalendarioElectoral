package com.tse.notificacioncalendarioelectoral;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tse.notificacioncalendarioelectoral.login.presenter.LoginContract;
import com.tse.notificacioncalendarioelectoral.login.presenter.LoginPresenter;
import com.tse.notificacioncalendarioelectoral.notification.PushNotificationActivity;

import javax.security.auth.callback.Callback;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_login extends Fragment implements LoginContract.View {


    private TextInputEditText txtmEmail;
    private TextInputEditText txtmPassword;
    private Button btnSignInButton;
    private View prsLoginProgress;
    private TextInputLayout txlEmailError;
    private TextInputLayout txlPasswordError;

    private LoginContract.Presenter mPresenter;

    private Callback mCallBack;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    public fragment_login() {
        // Required empty public constructor
    }


    public static fragment_login newInstance(){
        fragment_login fragmentLogin = new fragment_login();
        return fragmentLogin;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){

        }

        firebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    showPushNotifications();
                } else {
                    Log.e(fragment_login.class.getSimpleName(), "Usuario no logi");
                }
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_fragment_login, container, false);
        prsLoginProgress = root.findViewById(R.id.login_progress);

        txtmEmail = (TextInputEditText) root.findViewById(R.id.txtEmail);
        txtmPassword = (TextInputEditText) root.findViewById(R.id.txtPassword);
        txlEmailError = (TextInputLayout) root.findViewById(R.id.til_email_error);
        txlPasswordError = (TextInputLayout) root.findViewById(R.id.til_error_password);

        btnSignInButton = (Button) root.findViewById(R.id.btn_sign);

        txtmEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txlEmailError.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txlPasswordError.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });


        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null){
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void attemptLogin(){
        mPresenter.attemptLogin(txtmEmail.getText().toString(), txtmPassword.getText().toString());
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Callback){
            mCallBack = (Callback) context;
        }else {
            throw new RuntimeException(context.toString() + " debe implementar Callback");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        if (presenter != null){
            mPresenter = presenter;
        }else {
            throw new RuntimeException("El presenter no puede ser nulo");
        }
    }

    @Override
    public void showProgress(boolean show) {
        prsLoginProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setEmailError(String error) {
        txlEmailError.setError(error);
    }

    @Override
    public void setPasswordError(String error) {
        txlPasswordError.setError(error);
    }

    @Override
    public void showLoginError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPushNotifications() {
        startActivity(new Intent(getActivity(), PushNotificationActivity.class));
        getActivity().finish();
    }

    @Override
    public void showGooglePlayServicesDialog(int errorCode) {
        mCallBack.onInvokeGooglePlayServices(errorCode);
    }

    @Override
    public void showGooglePlayServicesError() {
        Toast.makeText(getActivity(), "Se requiere Google Play services para usar la app", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNetworkError() {
        Toast.makeText(getActivity(), "La red no esta disponible. Conectese y vuelva a intertarlo", Toast.LENGTH_LONG).show();
    }

    interface Callback {
        void onInvokeGooglePlayServices(int codeError);
    }

}
