package com.spartapps.livedata.http;

import androidx.lifecycle.MutableLiveData;

import com.spartapps.livedata.MovieResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private static MoviesRepository photoRepository;
    private final MutableLiveData<MovieResult> moviesData = new MutableLiveData<>();

    public static MoviesRepository getInstance(){
        if (photoRepository == null){
            photoRepository = new MoviesRepository();
        }
        return photoRepository;
    }

    private final MoviesApi newsApi;

    public MoviesRepository(){
        newsApi = MovieClientInstance.cteateService(MoviesApi.class);
    }

    public MutableLiveData<MovieResult> searchMovie(String qurey, int page){

        newsApi.searchMovies(qurey,page).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                moviesData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                moviesData.setValue(null);

            }
        });

        return moviesData;

    }
}
