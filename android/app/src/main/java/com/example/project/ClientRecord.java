//서버(Record)에 데이터 올리는 클라이언트
package com.example.project;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRecord {
    private static Retrofit retrofit;
    //서버 주소 중 바뀌지 않는 부분 = baseUrl
    private static String baseUrl = "http://192.168.0.156:5000/record/";

    //레트로핏 인스턴스 생성
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null ) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create()) //Gson -> json 변환기 생성
                    .build();
        }
        return retrofit;
    }
}
