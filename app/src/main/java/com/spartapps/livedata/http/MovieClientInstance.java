package com.spartapps.livedata.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieClientInstance {

    public final static String api_key = "YOUR_API_KEY";
    public final static String photo_url = "https://image.tmdb.org/t/p/w500/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static <S> S cteateService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
