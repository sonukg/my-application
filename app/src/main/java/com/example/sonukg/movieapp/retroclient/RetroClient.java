package com.example.sonukg.movieapp.retroclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClient {
    private static final String BASE_URL="http://api.themoviedb.org/3/";
    private Retrofit retrofit=null;
    public Retrofit getRetrofit()
    {
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory
                .create())
                .build();
        return retrofit;

    }

}
