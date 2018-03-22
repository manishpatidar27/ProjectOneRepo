package com.androidgaming.webservice;






import android.content.Context;
import android.util.Log;

import com.androidgaming.helper.Constants;
import com.androidgaming.helper.CustomPreference;
import com.androidgaming.helper.PreferenceKeys;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * WebServiceInterface Factory for REST Client Retrofit
 */
public class ApiFactory {

    public static final String BASE_URL = "http://patrongaming.xindevserver.com/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofitWithHeader = null;

    public static Retrofit getRetrofitClient() {


        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).
                    connectTimeout(5, TimeUnit.MINUTES).
                    readTimeout(5, TimeUnit.MINUTES).
                    writeTimeout(5, TimeUnit.MINUTES).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }




    public static Retrofit getRetrofitClientWithHeader(final Context context) {


        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder();

//                if (! CustomPreference.getInstance(context).getValue(PreferenceKeys.API_TOKEN).equals("")) {
//                    requestBuilder.addHeader("access-token",  CustomPreference.getInstance(context).getValue(PreferenceKeys.API_TOKEN));
//                }

                requestBuilder.addHeader("accept", "application/json");
                requestBuilder.addHeader("Content-Type", "application/json");
                Request request = requestBuilder.build();

                return chain.proceed(request);
            }
        });


        httpClientBuilder.connectTimeout(5, TimeUnit.MINUTES);
        httpClientBuilder.readTimeout(5, TimeUnit.MINUTES);
        httpClientBuilder.writeTimeout(5, TimeUnit.MINUTES);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(loggingInterceptor);

        List<Interceptor> list = httpClientBuilder.interceptors();

        for (int i = 0; i < list.size(); i++) {

            Log.e(Constants.TAG_LOGCAT, "INTERCEPTOR FOR POSITION " + i + " " + list.get(i).getClass());


        }

        if (retrofitWithHeader == null) {
            retrofitWithHeader = new Retrofit.Builder().baseUrl(BASE_URL).client(httpClientBuilder.build()).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofitWithHeader;


    }












}
