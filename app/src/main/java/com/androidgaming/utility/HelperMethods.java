package com.androidgaming.utility;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;


import com.androidgaming.helper.HTTPKeys;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okio.Buffer;
import retrofit2.Response;


public class HelperMethods {


    public static void closeKeyBoard(Context context) {
        try {
            View view = ((Activity) context).getWindow().getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {


        }
    }

    public static boolean isMyServiceRunning(Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {

//            if (GenerateCodeService.class.getCanonicalName().equals(service.service.getClassName())) {
//
//                return true;
//            }
        }

        return false;
    }


    public static String formatPrice(String priceComing) {

        String outPut = "";
        try {


            Double price = Double.parseDouble(priceComing);
            DecimalFormat df = new DecimalFormat("0.00");

            df.setMaximumFractionDigits(2);
            outPut = df.format(price);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.valueOf(outPut);
    }

    public static String responseYesSuccessful(Response<ResponseBody> response) {
        String stringResponse = "";


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            stringResponse = sb.toString();


            return stringResponse;
        } catch (IOException e) {

        }


        return stringResponse;

    }


    public static void responseNotSuccessful(Response<ResponseBody> response, Context mContext) {
        String stringResponse = "";
        try {


            if (!response.isSuccessful()) {


                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }

                    stringResponse = sb.toString();


                    if (response.code() == 401) {

                        // 401 Unauthorized
                        DialogConstant.showErrorMessageHTTP(mContext, HTTPKeys.HTTP_UN_AUTHORISED_ACCESS);

                    } else if (response.code() == 400) {

                        // 401 Unauthorized
                        JSONObject responseJson = new JSONObject(stringResponse);
                        DialogConstant.showMessageFromServer(mContext, responseJson.getString("message").toString());


                    } else if (response.code() == 403) {

                        // 403 Forbidden
                        JSONObject responseJson = new JSONObject(stringResponse);
                        DialogConstant.showMessageFromServer(mContext, responseJson.getString("message").toString());


                    } else if (response.code() == 404) {


//                        DialogConstant.showErrorMessageHTTP(mContext, HTTPKeys.HTTP_PAGE_NOT_FOUND);

                        JSONObject responseJson = new JSONObject(stringResponse);
                        DialogConstant.showMessageFromServer(mContext, responseJson.getString("message").toString());

                    } else if (response.code() == 405) {


                        DialogConstant.showErrorMessageHTTP(mContext, HTTPKeys.HTTP_RESOURCE_NOT_ALLOWED);

                    } else if (response.code() == 408) {

                        // 408 Request Timeout
                        // The client did not produce a request within the time that the server was prepared to wait.
                        // The client MAY repeat the request without modifications at any later time
                        DialogConstant.showErrorMessageHTTP(mContext, HTTPKeys.HTTP_UN_AUTHORISED_ACCESS);

                    } else if (response.code() == 500) {

                        // 500 Internal server error
                        // Internal Server Error. The 500 status code, or Internal Server Error, means that server cannot process
                        // the request for an unknown reason. Sometimes this code will appear when more specific 5xx errors are more appropriate
                        JSONObject responseJson = new JSONObject(stringResponse);
                        DialogConstant.showMessageFromServer(mContext, responseJson.getString("message").toString());

                    } else if (response.code() == 504) {

                        // 504
                        // The server, while acting as a gateway or proxy, did not receive a timely response
                        // from the upstream server specified by the URI (e.g. HTTP, FTP, LDAP) or
                        // some other auxiliary server (e.g. DNS) it needed to access in attempting to complete the request.

                        JSONObject responseJson = new JSONObject(stringResponse);


                        DialogConstant.showMessageFromServer(mContext, responseJson.getString("message").toString());

                        // DialogConstant.showErrorMessageHTTP(mContext, HTTPKeys.HTTP_GATE_WAY_TIME_OUT);

                    } else if (response.code() == 598) {

                        // 598
                        // This status code is not specified in any RFCs, but is used by some HTTP proxies
                        // to signal a network read timeout behind the proxy to a client in front of the proxy.

                        DialogConstant.showErrorMessageHTTP(mContext, HTTPKeys.HTTP_NETWORK_READ_TIME_OUT);

                    } else if (response.code() == 599) {

                        // 599
                        // This status code is not specified in any RFCs, but is used by some HTTP proxies to signal a
                        // network connect timeout behind the proxy to a client in front of the proxy.
                        DialogConstant.showErrorMessageHTTP(mContext, HTTPKeys.HTTP_NETWORK_CONNECT_TIME_OUT);

                    } else {

                        if (!stringResponse.equals("")) {

                            JSONObject responseJson = new JSONObject(stringResponse);

                            Object errorObject = responseJson.get("error");

                            if (errorObject instanceof JSONObject) {

                                DialogConstant.showMessageFromServer(mContext, responseJson.getJSONObject("error").getString("message"));

                            } else if (errorObject instanceof JSONArray) {

                                DialogConstant.showMessageFromServer(mContext, responseJson.getJSONArray("error").getJSONObject(0).getString("message"));
                            }

                        } else {

                            DialogConstant.showErrorMessageHTTP(mContext, HTTPKeys.HTTP_SOME_OTHER_ERROR);
                        }
                    }

                } catch (IOException e) {

                    DialogConstant.showErrorMessageHTTP(mContext, HTTPKeys.HTTP_SOME_OTHER_ERROR);


                } catch (Exception e) {

                    DialogConstant.showErrorMessageHTTP(mContext, HTTPKeys.HTTP_SOME_OTHER_ERROR);


                }
            }


        } catch (Exception e) {


        }
    }


    public static String bodyToString(RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }




    public static String hmacDigest(String msg, String keyString, String algo) {
        String digest = null;
        try {
            SecretKeySpec key = new SecretKeySpec((keyString).getBytes("UTF-8"), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(key);
            byte[] bytes = mac.doFinal(msg.getBytes("ASCII"));
            StringBuffer hash = new StringBuffer();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    hash.append('0');
                }
                hash.append(hex);
            }
            digest = hash.toString();
        } catch (UnsupportedEncodingException e) {
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        }
        return digest;
    }







    public static String getTimeIn12HoursBy24(final String strInput) {
        String output = "";
//        SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat formatRequired = new SimpleDateFormat("hh:mm a");
        Date date = null;
        try {
            date = formatTime.parse(strInput);

            output = formatRequired.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }


    public static String getRequiredFormatHomeScreen(final String strInput) {
        String output = "";
        SimpleDateFormat formatTime = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat formatRequired = new SimpleDateFormat("MMM-dd-yyyy");
        Date date = null;
        try {
            date = formatTime.parse(strInput);

            output = formatRequired.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static String makeFirstLetterUpperCase(String nameP) {

        String name = "";

        try {

            name = nameP.substring(0, 1).toUpperCase() + nameP.substring(1);

            return name;

        } catch (Exception e) {


            return nameP;
        }
    }

    public static String makeFirstLetterUpperAndOtherLower(String nameP) {

        String name = "";

        try {

            name = nameP.substring(0, 1).toUpperCase() + nameP.substring(1).toLowerCase();

            return name;

        } catch (Exception e) {


            return nameP;
        }
    }

    public static int calculateWidthOfScreen(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return displayMetrics.heightPixels;
    }


    public static int calculateHeightOfScreen(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }


//    public static boolean isEmailValid(String email) {
//        String regExpn =
//                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
//                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
//                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
//                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
//                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
//
//        CharSequence inputStr = email;
//
//        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(inputStr);
//
//        if (matcher.matches())
//            return true;
//        else
//            return false;
//    }


    public static boolean isEmailValid(String email) {

        // String regExpn = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String regExpn = "[a-zA-Z0-9_\\.\\+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-\\.]+";


        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public static void makeLinks(TextView textView, String[] links, ClickableSpan[] clickableSpans) {
        SpannableString spannableString = new SpannableString(textView.getText());
        for (int i = 0; i < links.length; i++) {
            ClickableSpan clickableSpan = clickableSpans[i];
            String link = links[i];

            int startIndexOfLink = textView.getText().toString().indexOf(link);
            spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }


    public static String getDateAfter30Days() {

        SimpleDateFormat requiredFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dateAfterMonth = "";
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Calendar.getInstance().getTime());
            //calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DAY_OF_YEAR, 30);
            dateAfterMonth = requiredFormat.format(calendar.getTime());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateAfterMonth;
    }

    public static String getDateFromUtc(String UTCStringObjFromServer) {
        try {
            SimpleDateFormat dateFormatUTCRev = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");
            dateFormatUTCRev.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date UTCFromServer = null;


            UTCFromServer = dateFormatUTCRev.parse(UTCStringObjFromServer);


            SimpleDateFormat dateFormatterLocal = new SimpleDateFormat("MM/dd/yyyy");
            dateFormatterLocal.setTimeZone(TimeZone.getDefault());
            String date = dateFormatterLocal.format(UTCFromServer);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            Log.v(Log.class.getName(), "getServerUTCTimeInLocalString Ex: " + e);
        }
        return "";
    }


    public static String getTimeAndDateFromUTC(String UTCStringObjFromServer) {
        try {
            SimpleDateFormat dateFormatUTCRev = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");
            dateFormatUTCRev.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date UTCFromServer = null;


            UTCFromServer = dateFormatUTCRev.parse(UTCStringObjFromServer);


            SimpleDateFormat dateFormatterLocal = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
            dateFormatterLocal.setTimeZone(TimeZone.getDefault());
            String date = dateFormatterLocal.format(UTCFromServer);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            Log.v(Log.class.getName(), "getServerUTCTimeInLocalString Ex: " + e);
        }
        return "";
    }


    public static String compareUTCDateFromCurrent(String UTCStringObjFromServer) {
        try {


            SimpleDateFormat dateFormatUTCRev = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");
            dateFormatUTCRev.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date utcDateFromServer = null;


            utcDateFromServer = dateFormatUTCRev.parse(UTCStringObjFromServer);

            SimpleDateFormat dateFormatterLocal = new SimpleDateFormat("MM/dd/yyyy");
            dateFormatterLocal.setTimeZone(TimeZone.getDefault());
            String date = dateFormatterLocal.format(utcDateFromServer);
            Log.e("TAG", "UTC DATE STRING : " + date);
            Date dateFromUTCtoLocalTime = dateFormatterLocal.parse(date);


            Date currentDateInMobile = null;


            try {

                SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
                dateFormatter.setTimeZone(TimeZone.getDefault());


                String currLocalDate = dateFormatter.format(Calendar.getInstance().getTime());
                Log.e("TAG", "MOBILE DATE : " + currLocalDate);
                currentDateInMobile = dateFormatter.parse(currLocalDate);


            } catch (Exception e) {

                Log.e("TAG", "COMING IN EXCEPTION : " + e.toString());
            }



        } catch (Exception e) {

            e.printStackTrace();

            return "";
        }

        return "";
    }


    public static String maskPhoneNumber(String number) {

        String MASK_NUMBER_10 = "XXX-XXX-XXXX";


        if (number.length() == 10) {

            int index = 0;
            StringBuilder masked = new StringBuilder();

            for (int i = 0; i < MASK_NUMBER_10.length(); i++) {
                char characterInMask = MASK_NUMBER_10.charAt(i);
                if (characterInMask == 'X') {
                    masked.append(number.charAt(index));
                    index++;
                } else {
                    masked.append(characterInMask);
                }
            }
            return masked.toString();
        } else {
            return number;
        }


    }


    public static String maskNumber(String number) {

        String MASK_CREDIT_CARD_16 = "XXXX-XXXX-XXXX-####";
        String MASK_CREDIT_CARD_19 = "XXXX-XXXX-XXXX-XXXX-####";
        String mask = "";
        if (number.length() == 16) {
            mask = MASK_CREDIT_CARD_16;
        } else {
            mask = MASK_CREDIT_CARD_19;
        }
        if (number.length() > 2) {

            int index = 0;
            StringBuilder masked = new StringBuilder();

            for (int i = 0; i < mask.length(); i++) {
                char characterInMask = mask.charAt(i);
                if (characterInMask == '#') {
                    masked.append(number.charAt(index));
                    index++;
                } else if (characterInMask == 'X') {
                    masked.append(characterInMask);
                    index++;
                } else {
                    masked.append(characterInMask);
                }
            }
            return masked.toString();
        } else {
            return "";
        }


    }

    public static boolean checkEmailManually(String strEmail) {


        if (
                strEmail.startsWith(".") ||
                        strEmail.contains(".-_") ||
                        strEmail.contains("_-.") ||
                        strEmail.contains("_.-") ||
                        strEmail.contains("@.") ||
                        strEmail.contains("..") ||
                        strEmail.contains(".@.") ||
                        strEmail.contains("__") ||
                        strEmail.contains("-@") ||
                        strEmail.contains("@-") ||
                        strEmail.contains("--") ||
                        strEmail.contains("..")
                ) {
            return true;
        } else {
            return false;
        }
    }

    public static CharSequence trim(CharSequence s, int start, int end) {
        while (start < end && Character.isWhitespace(s.charAt(start))) {
            start++;
        }

        while (end > start && Character.isWhitespace(s.charAt(end - 1))) {
            end--;
        }

        return s.subSequence(start, end);
    }




}
