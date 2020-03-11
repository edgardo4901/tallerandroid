package com.talleres.proyectotalleresandroid.Services;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit retrofit = null;

    public RetrofitClient() {}

    public static Retrofit getClient(String baseUrl) {
        String burl = "";
        if(retrofit != null)
        {
            burl = retrofit.baseUrl().toString();
        }
        if (retrofit == null || !burl.equals(baseUrl)) {

            OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60 * 5, TimeUnit.SECONDS)
                    .readTimeout(60 * 5, TimeUnit.SECONDS)
                    .writeTimeout(60 * 5, TimeUnit.SECONDS);

            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient.build())
                    .build();
        }
        return retrofit;
    }

    public static APIGeneral getAPIService(String url) {
        return getClient(url).create(APIGeneral.class);
    }

}
