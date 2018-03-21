package com.androidgaming.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidgaming.R;
import com.androidgaming.helper.ProgressHUD;
import com.androidgaming.utility.CheckInternet;
import com.androidgaming.utility.DialogConstant;
import com.androidgaming.utility.HelperMethods;

import java.util.Arrays;


//This is added for git first commit
public class LoginActivity extends AppCompatActivity {


    private ProgressHUD progressHUD;
    private EditText editText_first_name;
    private EditText editText_password;
    private TextView login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressHUD = ProgressHUD.init(LoginActivity.this);


        initViews();

    }

    private void initViews() {

        editText_first_name = (EditText) findViewById(R.id.editText_first_name);
        editText_password = (EditText) findViewById(R.id.editText_password);
        login_button = (TextView) findViewById(R.id.login_button);
        login_button.setOnClickListener(listenersForTheScreen);

    }


    View.OnClickListener listenersForTheScreen = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {

                case R.id.login_button:


                    validateFields();
                    break;


            }
        }
    };

    private void validateFields() {


        if (editText_first_name.getText().toString().equals("")) {
            DialogConstant.showValidationMessage(LoginActivity.this, getResources().getString(R.string.val_user_name));
            return;
        }

//        if (HelperMethods.checkEmailManually(editText_first_name.getText().toString())) {
//
//            DialogConstant.showValidationMessage(LoginActivity.this, getResources().getString(R.string.val_email_not_valid));
//
//            return;
//        }
//
//
//        if (HelperMethods.isEmailValid(editText_first_name.getText().toString())) {
//
//            DialogConstant.showValidationMessage(LoginActivity.this, getResources().getString(R.string.val_email_not_valid));
//
//            return;
//        }


        if (editText_password.getText().toString().length() < 1) {
            DialogConstant.showValidationMessage(LoginActivity.this, getResources().getString(R.string.val_password));
            return;
        }


        if (CheckInternet.isInternetOn(LoginActivity.this)) {

            progressHUD.show();


            if (editText_first_name.getText().toString().equalsIgnoreCase("faheem") &&
                    editText_password.getText().toString().equalsIgnoreCase("faheem")) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_LONG).show();
                        progressHUD.hide();
                    }
                }, 5000);



            } else {

                new Handler().postDelayed(new Runnable() {
                      @Override
                      public void run() {
                          DialogConstant.showValidationMessage(LoginActivity.this, getResources().getString(R.string.user_name_pass_not_matched));
                          progressHUD.hide();
                      }
                }, 5000);

                return;
            }

        } else {

            DialogConstant.showInternetNotWorking(LoginActivity.this);

        }

    }
}
