package com.androidgaming.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidgaming.R;
import com.androidgaming.helper.ProgressHUD;

public class LoginActivity extends AppCompatActivity {
    ProgressHUD progressHUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressHUD = new ProgressHUD(LoginActivity.this);
        progressHUD.show();

    }
}
