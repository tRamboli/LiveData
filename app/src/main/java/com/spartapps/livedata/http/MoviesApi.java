package com.spartapps.livedata.http;

import com.spartapps.livedata.MovieResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApi {
    @GET("search/movie?api_key="+MovieClientInstance.api_key+"&language=en-US&include_adult=false")
    Call<MovieResult> searchMovies(@Query("query") String query, @Query("page") int page);
}

