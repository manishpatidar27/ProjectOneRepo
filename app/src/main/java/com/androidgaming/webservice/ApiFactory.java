package com.androidgaming.webservice;






import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * WebServiceInterface Factory for REST Client Retrofit
 */
public class ApiFactory {

    public static final String BASE_URL = "";
    private static Retrofit retrofit = null;

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

}
