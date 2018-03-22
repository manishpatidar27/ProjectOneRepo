package com.androidgaming.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.androidgaming.R;
import com.androidgaming.helper.CustomPreference;
import com.androidgaming.helper.PreferenceKeys;


public class SplashScreen extends AppCompatActivity {


    private final static int SPLASH_TIME_OUT = 2000;
    String accessToken = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);
        if ( CustomPreference.getInstance(SplashScreen.this).getValue(PreferenceKeys.API_TOKEN).equals(""))
        {
            loadSplash();
        }
        else
        {navigateToHomeScreen();

        }



    }


    private void navigateToHomeScreen()
    {
        Intent intent = new Intent(SplashScreen.this , HomeScreen.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.right_to_left_start, R.anim.right_to_left_end);
    }







    private void loadSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loadScreen();

            }
        }, SPLASH_TIME_OUT);
    }

    private void loadScreen() {

        Intent intentToLoginActivity11 = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(intentToLoginActivity11);
        finish();
        overridePendingTransition(R.anim.right_to_left_start, R.anim.right_to_left_end);
    }

}
