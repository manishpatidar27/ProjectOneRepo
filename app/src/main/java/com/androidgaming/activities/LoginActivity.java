package com.androidgaming.activities;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidgaming.R;
import com.androidgaming.helper.CustomPreference;
import com.androidgaming.helper.HTTPKeys;
import com.androidgaming.helper.PreferenceKeys;
import com.androidgaming.helper.ProgressHUD;
import com.androidgaming.utility.CheckInternet;
import com.androidgaming.utility.DialogConstant;
import com.androidgaming.utility.HelperMethods;
import com.androidgaming.webservice.ApiFactory;
import com.androidgaming.webservice.WebServiceInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//This is added for git first commit
public class LoginActivity extends AppCompatActivity {


    private ProgressHUD progressHUD;
    private EditText editText_first_name;
    private EditText editText_password;
    private TextView login_button;
    private TextView forgot_password;

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
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        forgot_password.setPaintFlags(forgot_password.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forgot_password.setText(LoginActivity.this.getResources().getString(R.string.forgot_your_password));

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

//            progressHUD.show();
//            if (editText_first_name.getText().toString().equalsIgnoreCase("faheem") &&
//                    editText_password.getText().toString().equalsIgnoreCase("faheem")) {
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(LoginActivity.this, "success", Toast.LENGTH_LONG).show();
//                        progressHUD.hide();
//                    }
//                }, 5000);
//
//
//
//            } else {
//
//                new Handler().postDelayed(new Runnable() {
//                      @Override
//                      public void run() {
//                          DialogConstant.showValidationMessage(LoginActivity.this, getResources().getString(R.string.user_name_pass_not_matched));
//                          progressHUD.hide();
//                      }
//                }, 5000);
//
//                return;
//            }


            callAPIForLogin();

        } else {

            DialogConstant.showInternetNotWorking(LoginActivity.this);

        }

    }


    private void navigateToHomeScreen() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        finish();

        overridePendingTransition(R.anim.right_to_left_start, R.anim.right_to_left_end);


    }

    private void callAPIForLogin() {

        Map<String, String> loginRequest = new HashMap<>();


        loginRequest.put("username", editText_first_name.getText().toString());
        loginRequest.put("password", editText_password.getText().toString());


        if (CheckInternet.isInternetOn(LoginActivity.this)) {
            progressHUD.show();
            WebServiceInterface api = ApiFactory.getRetrofitClientWithHeader().create(WebServiceInterface.class);
            Call<ResponseBody> call = null;
            call = api.login(loginRequest);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    progressHUD.hide();
                    if (response.isSuccessful()) {
                        try {
                            JSONObject responseJson = new JSONObject(HelperMethods.responseYesSuccessful(response));
                            Log.e("TAG ", "Response of Login Api are " + responseJson.toString());
                            if (responseJson.getString("status").equalsIgnoreCase("true")) {

                                Toast.makeText(LoginActivity.this, responseJson.getString("message"), Toast.LENGTH_LONG).show();


                                CustomPreference.getInstance(LoginActivity.this).addValue(PreferenceKeys.USER_ID, responseJson.getJSONObject("data").getString("id"));
                                CustomPreference.getInstance(LoginActivity.this).addValue(PreferenceKeys.USER_NAME, responseJson.getJSONObject("data").getString("name"));
                                CustomPreference.getInstance(LoginActivity.this).addValue(PreferenceKeys.USER_EMAIL, responseJson.getJSONObject("data").getString("email"));
                                CustomPreference.getInstance(LoginActivity.this).addValue(PreferenceKeys.USER_USERNAME, responseJson.getJSONObject("data").getString("username"));
                                CustomPreference.getInstance(LoginActivity.this).addValue(PreferenceKeys.USER_TYPE, responseJson.getJSONObject("data").getString("usertype"));
                                CustomPreference.getInstance(LoginActivity.this).addValue(PreferenceKeys.USER_LOCATION, responseJson.getJSONObject("data").getString("location"));
                                CustomPreference.getInstance(LoginActivity.this).addValue(PreferenceKeys.API_TOKEN, responseJson.getJSONObject("data").getString("api_token"));
                                CustomPreference.getInstance(LoginActivity.this).addValue(PreferenceKeys.USER_IS_ACTIVE, responseJson.getJSONObject("data").getString("is_active"));
                                navigateToHomeScreen();

                            } else if (responseJson.getString("status").equalsIgnoreCase("false")) {

                                DialogConstant.showMessageFromServer(LoginActivity.this, responseJson.getString("message"));
                            }


                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    } else if (!response.isSuccessful()) {


                        try {
                            HelperMethods.responseNotSuccessful(response, LoginActivity.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressHUD.hide();
                    DialogConstant.showErrorMessageHTTP(LoginActivity.this, HTTPKeys.RETROFIT_FAILURE);

                }
            });
        } else {
            DialogConstant.showInternetNotWorking(LoginActivity.this);
        }


    }
}
