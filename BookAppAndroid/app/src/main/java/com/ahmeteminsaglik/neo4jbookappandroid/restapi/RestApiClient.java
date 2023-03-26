package com.ahmeteminsaglik.neo4jbookappandroid.restapi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiClient {

    private static Retrofit getRetrofit(String url) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS);
        OkHttpClient okHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }

    public static RestApiUserService getUserRestApi(String url) {
        Retrofit retrofit = getRetrofit(url);
        RestApiUserService restAPIUserService = retrofit.create(RestApiUserService.class);
        return restAPIUserService;
    }

    public static RestApiBookService getBookRestApi(String url) {
        Retrofit retrofit = getRetrofit(url);
        RestApiBookService restApiBookService = retrofit.create(RestApiBookService.class);
        return restApiBookService;
    }

}
