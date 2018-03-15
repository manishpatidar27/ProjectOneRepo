package com.androidgaming.utility;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.androidgaming.R;
import com.androidgaming.activities.LoginActivity;
import com.androidgaming.helper.HTTPKeys;


/**
 * Created by Manish Patidar on 14-08-2017.
 */

public class DialogConstant {


    public static void showInternetNotWorking(final Context context) {


        final Dialog alertDialog = new Dialog(context);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setContentView(R.layout.dialog_single_button);


        TextView dialog_header = (TextView) alertDialog.findViewById(R.id.dialog_header);
        TextView dialog_message = (TextView) alertDialog.findViewById(R.id.dialog_message);
        TextView button_ok = (TextView) alertDialog.findViewById(R.id.button_ok);

        dialog_header.setText(R.string.title_dialog_alert_validation);
        dialog_message.setText(R.string.message_dialog_for_internet);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    public static void showErrorMessageHTTP(final Context contextP, HTTPKeys responseCode) {


        final Dialog alertDialog = new Dialog(contextP);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setContentView(R.layout.dialog_single_button);


        TextView dialog_header = (TextView) alertDialog.findViewById(R.id.dialog_header);
        TextView dialog_message = (TextView) alertDialog.findViewById(R.id.dialog_message);
        TextView button_ok = (TextView) alertDialog.findViewById(R.id.button_ok);


        switch (responseCode) {

            case HTTP_UN_AUTHORISED_ACCESS: {
                //401 Unauthorized
                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_un_authorised_access));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;

            case HTTP_FORBIDDEN: {
                //401 Unauthorized
                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_forbidden));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;


            case HTTP_PAGE_NOT_FOUND: {
                //404 Method Not Allowed

                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_page_not_found));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;


            case HTTP_RESOURCE_NOT_ALLOWED: {
                //405 Method Not Allowed

                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_resource_not_allowed));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;

            case HTTP_REQUEST_TIME_OUT: {
                // 408 Request Timeout
                // The client did not produce a request within the time that the server was prepared to wait.
                // The client MAY repeat the request without modifications at any later time

                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_request_time_out));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;

            case HTTP_INTERNAL_SERVER_ERROR: {
                // 500 Internal server error
                // Internal Server Error. The 500 status code, or Internal Server Error, means that server cannot process
                // the request for an unknown reason. Sometimes this code will appear when more specific 5xx errors are more appropriate


                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_internal_server_error));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;


            case HTTP_GATE_WAY_TIME_OUT: {
                // 504
                // The server, while acting as a gateway or proxy, did not receive a timely response
                // from the upstream server specified by the URI (e.g. HTTP, FTP, LDAP) or
                // some other auxiliary server (e.g. DNS) it needed to access in attempting to complete the request.

                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_gate_way_time_out));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;

            case HTTP_NETWORK_READ_TIME_OUT: {
                //598
                // This status code is not specified in any RFCs, but is used by some HTTP proxies
                // to signal a network read timeout behind the proxy to a client in front of the proxy.

                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_network_read_time_out_error));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;

            case HTTP_NETWORK_CONNECT_TIME_OUT: {
                //599
                // This status code is not specified in any RFCs, but is used by some HTTP proxies to signal a
                // network connect timeout behind the proxy to a client in front of the proxy.

                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_network_connect_time_out_error));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;

            case HTTP_SOME_OTHER_ERROR: {
                // This status code is not specified in any RFCs, but is used by some HTTP proxies to signal a
                // network connect timeout behind the proxy to a client in front of the proxy.


                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_some_error_occurred));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;

            case RETROFIT_FAILURE: {
                // This is called when retrofit fails in the application


                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_some_error_occurred));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();


            }
            break;

            default:


                dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
                dialog_message.setText(contextP.getResources().getString(R.string.message_dialog_some_error_occurred));
                button_ok.setText(contextP.getResources().getString(R.string.button_dialog_ok));
                button_ok.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();

                break;


        }


    }


    public static void showMessageFromServer(final Context contextP, final String message) {


        final Dialog alertDialog = new Dialog(contextP);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setContentView(R.layout.dialog_single_button);

        TextView dialog_header = (TextView) alertDialog.findViewById(R.id.dialog_header);
        TextView dialog_message = (TextView) alertDialog.findViewById(R.id.dialog_message);
        TextView button_ok = (TextView) alertDialog.findViewById(R.id.button_ok);

        dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));


            dialog_message.setText(contextP.getResources().getString(R.string.session_expire));


        button_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                    try {


                        Intent intent = new Intent(contextP, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        contextP.startActivity(intent);
                        ((Activity) contextP).finish();

                    } catch (Exception e) {
                        alertDialog.dismiss();
                    }



            }
        });
        alertDialog.show();


    }

    public static void showValidationMessage(final Context contextP, final String message) {


        final Dialog alertDialog = new Dialog(contextP);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setContentView(R.layout.dialog_single_button);

        TextView dialog_header = (TextView) alertDialog.findViewById(R.id.dialog_header);
        TextView dialog_message = (TextView) alertDialog.findViewById(R.id.dialog_message);
        TextView button_ok = (TextView) alertDialog.findViewById(R.id.button_ok);

        dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
        dialog_message.setText(message);

        button_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

            }
        });
        alertDialog.show();


    }


    public static void showValidationMessage(final Context contextP, final String message, final boolean activityClose) {


        final Dialog alertDialog = new Dialog(contextP);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setContentView(R.layout.dialog_single_button);

        TextView dialog_header = (TextView) alertDialog.findViewById(R.id.dialog_header);
        TextView dialog_message = (TextView) alertDialog.findViewById(R.id.dialog_message);
        TextView button_ok = (TextView) alertDialog.findViewById(R.id.button_ok);

        dialog_header.setText(contextP.getResources().getString(R.string.title_dialog_alert_validation));
        dialog_message.setText(message);

        button_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (activityClose == true) {
                    alertDialog.dismiss();
                    ((Activity) contextP).finish();
                } else {
                    alertDialog.dismiss();
                }

            }
        });

        alertDialog.show();

    }


    public interface OnConfirmedListener {

        void onConfirmed();
    }

    public interface OnAskQuestionListener {

        void onAccepted();

        void onDeclined();

    }


    public static DialogConstant.OnAskQuestionListener onAskQuestionListener;

    public static void showDialogForConfirmation(Context context, String messageStr, final OnConfirmedListener onConfirmedListener) {


        final Dialog alertDialog = new Dialog(context);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setContentView(R.layout.dialog_single_button);

        TextView dialog_header = (TextView) alertDialog.findViewById(R.id.dialog_header);
        TextView dialog_message = (TextView) alertDialog.findViewById(R.id.dialog_message);
        TextView button_ok = (TextView) alertDialog.findViewById(R.id.button_ok);

        dialog_header.setText(context.getResources().getString(R.string.title_dialog_message));
        dialog_message.setText(messageStr);

        button_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onConfirmedListener.onConfirmed();
                alertDialog.dismiss();

            }
        });

        alertDialog.show();


    }


    public static void showDialogForConfirmation(Context context, String titleText, String messageStr, String buttonText, final OnConfirmedListener onConfirmedListener) {


        final Dialog alertDialog = new Dialog(context);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setContentView(R.layout.dialog_single_button);

        TextView dialog_header = (TextView) alertDialog.findViewById(R.id.dialog_header);
        dialog_header.setText(titleText);


        TextView dialog_message = (TextView) alertDialog.findViewById(R.id.dialog_message);
        dialog_message.setText(messageStr);

        TextView button_ok = (TextView) alertDialog.findViewById(R.id.button_ok);
        button_ok.setText(buttonText);


        button_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onConfirmedListener.onConfirmed();
                alertDialog.dismiss();

            }
        });

        alertDialog.show();


    }


    public static void showDialogForAskQuestion(Context context, String title, String messageStr, String positiveButton, String negativeButton, final OnAskQuestionListener listener) {


        final Dialog alertDialog = new Dialog(context);

        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.setContentView(R.layout.dialog_double_button);

        TextView dialog_header = (TextView) alertDialog.findViewById(R.id.dialog_header);
        dialog_header.setText(title);

        TextView dialog_message = (TextView) alertDialog.findViewById(R.id.dialog_message);
        dialog_message.setText(messageStr);


        TextView button_cancel = (TextView) alertDialog.findViewById(R.id.button_cancel);
        button_cancel.setText(negativeButton);


        TextView button_ok = (TextView) alertDialog.findViewById(R.id.button_ok);
        button_ok.setText(positiveButton);


        button_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onAccepted();
                alertDialog.dismiss();

            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onDeclined();
                alertDialog.dismiss();

            }
        });


        alertDialog.show();


    }
}
