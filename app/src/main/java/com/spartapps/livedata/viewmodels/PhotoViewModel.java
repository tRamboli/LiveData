package com.spartapps.livedata.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.spartapps.livedata.Photo;
import com.spartapps.livedata.http.PhotoRepository;

import java.util.List;

public class PhotoViewModel extends ViewModel {

    private MutableLiveData<List<Photo>> mutableLiveData;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        PhotoRepository photoRepository = PhotoRepository.getInstance();
        mutableLiveData = photoRepository.getPhotos();

    }

    public LiveData<List<Photo>> getPhotoRepository() {
        return mutableLiveData;
    }

}
