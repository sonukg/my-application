package com.example.sonukg.movieapp.retrointerface;

import com.example.sonukg.movieapp.model.UserModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetroInterface {
    @GET("movie/upcoming")
    Call<UserModel> getDetails(@Query("api_key") String api_key);

}