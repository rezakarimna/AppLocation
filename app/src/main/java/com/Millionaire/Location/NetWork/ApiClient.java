package com.Millionaire.Location.NetWork;

import android.content.Context;
import android.content.SharedPreferences;

import com.Millionaire.Location.Setting.SharedPrefManage;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "datasnap/rest/TserverMethods1/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(450 , TimeUnit.MINUTES)
            .writeTimeout(45 , TimeUnit.MINUTES)
            .connectTimeout(45,TimeUnit.MINUTES)
            .build();

    public static Retrofit getRetrofit(Context context){
        if (retrofit == null){
           SharedPrefManage sharedPrefManage = new SharedPrefManage(context);
            retrofit = new Retrofit.Builder().baseUrl(sharedPrefManage.getSaveUrl() + "api/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
