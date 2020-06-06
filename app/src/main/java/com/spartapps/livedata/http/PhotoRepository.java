package com.spartapps.livedata.http;

import androidx.lifecycle.MutableLiveData;

import com.spartapps.livedata.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoRepository {

    private static PhotoRepository photoRepository;

    public static PhotoRepository getInstance(){
        if (photoRepository == null){
            photoRepository = new PhotoRepository();
        }
        return photoRepository;
    }

    private final NewsApi newsApi;

    public PhotoRepository(){
        newsApi = PhotoClientInstance.cteateService(NewsApi.class);
    }

    public MutableLiveData<List<Photo>> getPhotos(){
        final MutableLiveData<List<Photo>> photosData = new MutableLiveData<>();

        newsApi.getPhotos().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                photosData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                photosData.setValue(null);

            }
        });

        return photosData;

    }
}
