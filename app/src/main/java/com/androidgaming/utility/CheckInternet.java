package com.androidgaming.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

public class CheckInternet {

    public static boolean isInternetOn(Context context) {

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {


                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();

                if (activeNetwork != null) {
                   // PrintLog.v("Internet ", "GREATER THAN OR EQUAL TO LOLLIPOP" + activeNetwork.isConnectedOrConnecting());
                    return activeNetwork.isConnectedOrConnecting();

                } else {

                    return false;
                }

            } else {

                //PrintLog.v("Internet ", "LESS THAN LOLLIPOP");
                if (connectivityManager != null) {
                    // noinspection deprecation
                    NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                    if (info != null) {
                        for (NetworkInfo anInfo : info) {
                            if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                                //PrintLog.v("internet ", "BELOW Lollipop NetworkInfo.State.CONNECTED");
                                return true;
                            }
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }



        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
