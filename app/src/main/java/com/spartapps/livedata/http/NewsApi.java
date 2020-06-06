package com.spartapps.livedata.http;

import com.spartapps.livedata.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApi {
    @GET("photos")
    Call<List<Photo>> getPhotos();
}

