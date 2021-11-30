package com.example.friendfinderapp.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {

    public static final String baseUrl = "http://192.168.2.19/friend-finder/public/";
    public static Retrofit retrofit;

    public static Retrofit konekRetro(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
