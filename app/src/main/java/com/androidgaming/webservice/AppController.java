package com.androidgaming.webservice;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.location.Location;
import android.util.Log;


import com.androidgaming.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class AppController extends Application {


    public static final String TAG = AppController.class.getSimpleName();


    private static AppController mInstance;


    public static synchronized AppController getInstance() {
        return mInstance;
    }

    private void init(Application app) {
        mInstance = this;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Log.v("APP CONTROLLER", "onCreate app class ");

//        if (FirebaseApp.getApps(this).isEmpty()) {
//            PrintLog.v("", "FIRE BASE IS EMPTY INITIALIZE IT  ");
//            FirebaseApp.initializeApp(this);
//            FirebaseAnalytics.getInstance(this);
//
//        } else {
//
//            Log.v("APP CONTROLLER", "FIRE BASE IS not empty  ");
//        }

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );



    }


}
