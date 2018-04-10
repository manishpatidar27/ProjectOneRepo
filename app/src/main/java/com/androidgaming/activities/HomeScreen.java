package com.androidgaming.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.androidgaming.R;
import com.androidgaming.helper.Constants;
import com.androidgaming.helper.CustomPreference;
import com.androidgaming.helper.PreferenceKeys;
import com.androidgaming.helper.ProgressHUD;
import com.androidgaming.utility.CheckInternet;
import com.androidgaming.utility.DialogConstant;
import com.androidgaming.utility.HelperMethods;
import com.androidgaming.webservice.ApiFactory;
import com.androidgaming.webservice.WebServiceInterface;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {
    LinearLayout feedBackLayout;
    LinearLayout dashBoardLayout;
    LinearLayout logoutLayout;
    private ProgressHUD progressHUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        initView();
        setOnClick();

    }

    private void setOnClick() {
        feedBackLayout.setOnClickListener(this);
        dashBoardLayout.setOnClickListener(this);
        logoutLayout.setOnClickListener(this);
    }

    private void initView()
    {
        progressHUD = ProgressHUD.init(HomeScreen.this);
        logoutLayout = (LinearLayout) findViewById(R.id.logoutLayout);
        dashBoardLayout = (LinearLayout) findViewById(R.id.dashBoardLayout);
        feedBackLayout = (LinearLayout) findViewById(R.id.feedBackLayout);
    }


    private void dashBoardApi()
    {
        WebServiceInterface api = ApiFactory.getRetrofitClientWithHeader().create(WebServiceInterface.class);
        Call<ResponseBody> call = null;
        call = api.logout(CustomPreference.getInstance(HomeScreen.this).getValue(PreferenceKeys.API_TOKEN));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                if (response.isSuccessful()) {
                    try {
                        JSONObject responseJson = new JSONObject(HelperMethods.responseYesSuccessful(response));
                        Log.e(Constants.TAG_LOGCAT , "Dash Board Screen Response are  =====>>>>>  "+responseJson.toString() );




                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }



            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    private void logoutApi()
    {
        progressHUD.show();
        WebServiceInterface api = ApiFactory.getRetrofitClientWithHeader().create(WebServiceInterface.class);
        Call<ResponseBody> call = null;
        call = api.logout(CustomPreference.getInstance(HomeScreen.this).getValue(PreferenceKeys.API_TOKEN));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject responseJson = new JSONObject(HelperMethods.responseYesSuccessful(response));
                        Log.e(Constants.TAG_LOGCAT , "Logout  Response of Api are =====>>>>>  "+responseJson.toString() );

                        if (responseJson.getString("status").equalsIgnoreCase("false"))
                        {
                            progressHUD.hide();
                            CustomPreference.getInstance(HomeScreen.this).clearSharedPreference();
                            navigateToLoginScreen();
                        } else
                        {
                            progressHUD.hide();

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void navigateToLoginScreen() {
        Intent intent = new Intent(HomeScreen.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedBackLayout:
                navigateToFeedBackScreen();
                break;
            case R.id.dashBoardLayout:
                navigateToGraphScreen();
                break;
            case R.id.logoutLayout:


                DialogConstant.showDialogForAskQuestion(HomeScreen.this, HomeScreen.this.getResources().getString(R.string.title_dialog_alert_validation),
                        HomeScreen.this.getResources().getString(R.string.log_out_message),
                        HomeScreen.this.getResources().getString(R.string.button_ok),
                        HomeScreen.this.getResources().getString(R.string.button_cancel), new DialogConstant.OnAskQuestionListener() {
                            @Override
                            public void onAccepted() {

                                if (CheckInternet.isInternetOn(HomeScreen.this))
                                {
                                    logoutApi();
                                }
                                else
                                {
                                    DialogConstant.showInternetNotWorking(HomeScreen.this);
                                }

                            }

                            @Override
                            public void onDeclined() {

                            }
                        });

                break;
        }


    }


    private void navigateToFeedBackScreen() {
        Intent intent = new Intent(this, FeedBackScreen.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left_start, R.anim.right_to_left_end);
    }


    private void navigateToGraphScreen() {


        Intent intent = new Intent(this, GraphInScroll.class);
//        Intent intent = new Intent(this, GraphScreenNew.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left_start, R.anim.right_to_left_end);
    }


}
