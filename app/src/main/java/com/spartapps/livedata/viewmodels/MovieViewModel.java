package com.spartapps.livedata.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.spartapps.livedata.MovieResult;
import com.spartapps.livedata.http.MoviesRepository;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<MovieResult> mutableLiveData;
    private MoviesRepository moviesRepository;
    public void init(){
        if (mutableLiveData != null){
            return;
        }
        moviesRepository = MoviesRepository.getInstance();

    }

    public void initSearch(String query, int page){
        mutableLiveData = moviesRepository.searchMovie(query,page);
    }

    public LiveData<MovieResult> getMovieRepository() {
        return mutableLiveData;
    }

}
