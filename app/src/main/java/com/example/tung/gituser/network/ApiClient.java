package com.example.tung.gituser.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit sRetrofit;
    private static OkHttpClient sOkHttpClient;

    public static Retrofit getClient() {
        if (sOkHttpClient == null) {
            initOkHttpClient();
        }
        if (sRetrofit == null) {
            initRetrofit();
        }
        return sRetrofit;
    }

    private static void initRetrofit() {
        sRetrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static void initOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(12000, TimeUnit.MILLISECONDS)
                .connectTimeout(12000, TimeUnit.MILLISECONDS)
                .writeTimeout(12000, TimeUnit.MILLISECONDS);
        sOkHttpClient = builder.build();
    }
}
