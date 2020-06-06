package com.spartapps.livedata.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spartapps.livedata.Photo;
import com.spartapps.livedata.R;
import com.spartapps.livedata.adapters.PhotosAdapter;
import com.spartapps.livedata.viewmodels.PhotoViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView photos_list;
    private final ArrayList<Photo> articleArrayList = new ArrayList<>();
    private PhotosAdapter photosAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
/*        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        photos_list = root.findViewById(R.id.photos_list);

        PhotoViewModel photoViewModel = new ViewModelProvider(this).get(PhotoViewModel.class);
        photoViewModel.init();

        photoViewModel.getPhotoRepository().observe(getViewLifecycleOwner(), photosResponse -> {
            articleArrayList.addAll(photosResponse);
            photosAdapter.notifyDataSetChanged();
        });
        setupRecyclerView();

        return root;
    }

    private void setupRecyclerView() {
        if (photosAdapter == null) {
            photosAdapter = new PhotosAdapter(articleArrayList);
            photos_list.setLayoutManager(new LinearLayoutManager(getContext()));
            photos_list.setAdapter(photosAdapter);
            photos_list.setItemAnimator(new DefaultItemAnimator());
            photos_list.setNestedScrollingEnabled(true);
        } else {
            photosAdapter.notifyDataSetChanged();
        }
    }
}