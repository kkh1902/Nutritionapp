package com.example.project;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Retrofit retrofit;
//    private static String baseUrl = "http://58.233.251.12:5000/";
    private static String baseUrl = "http://192.168.0.156:5000/";
//    private static String baseUrl = "http://20.30.26.200:5000/";

    public static Retrofit getRetrofitInstance() {
//        if (retrofit == null ) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        }
        return retrofit;
    }
}
