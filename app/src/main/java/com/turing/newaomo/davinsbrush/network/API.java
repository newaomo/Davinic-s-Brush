package com.turing.newaomo.davinsbrush.network;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by newao on 2018/2/18.
 */

public class API {

    private static RetrofitAPI retrofitAPI;

    public static RetrofitAPI Retrofit() {

        final OkHttpClient client = new OkHttpClient.Builder().
                connectTimeout(60, TimeUnit.SECONDS).
                readTimeout(60, TimeUnit.SECONDS).
                writeTimeout(60, TimeUnit.SECONDS).build();

        if (retrofitAPI == null) {

            retrofitAPI = new Retrofit.Builder()
                    .baseUrl("http://aeacf450.ngrok.io/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitAPI.class);
        }
        return retrofitAPI;
    }

    public interface RetrofitAPI {

        //图片上传
        @Multipart
        @POST("mainserver")
        Call<String> updateImage(@PartMap Map<String, RequestBody> params);

    }

}