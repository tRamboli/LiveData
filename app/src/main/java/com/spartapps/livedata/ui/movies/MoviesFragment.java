package com.spartapps.livedata.ui.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spartapps.livedata.MainActivity;
import com.spartapps.livedata.Movie;
import com.spartapps.livedata.R;
import com.spartapps.livedata.adapters.MovieAdapter;
import com.spartapps.livedata.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class MoviesFragment extends Fragment {

    private RecyclerView movies_list;
    private final ArrayList<Movie> articleArrayList = new ArrayList<>();
    private MovieAdapter moviesAdapter;
    private MovieViewModel moviesViewModel;
    private int page=1;
    private String lastSearch = "a";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_movies, container, false);

        movies_list = root.findViewById(R.id.movies_list);

        moviesViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        moviesViewModel.init();
        LoadMovies();

        moviesViewModel.getMovieRepository().observe(getViewLifecycleOwner(), movieResult -> {
            if(movieResult!=null) {

                articleArrayList.addAll(movieResult.getResults());
                moviesAdapter.notifyDataSetChanged();
            }
            });
        setupRecyclerView();


        movies_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == articleArrayList.size() - 3) {
                        LoadMovies();
                    }

            }
        });

        setHasOptionsMenu(true);


        return root;


    }

    private void Sort(){
        Collections.sort(articleArrayList, (o1, o2) -> {
            if(o1.getRelease_date()!=null && o2.getRelease_date()!=null){
                int result = o1.getRelease_date().compareTo(o2.getRelease_date());
                return Integer.compare(0, result);
            }else
                return 0;
        });
    }

    private void LoadMovies(){
        moviesViewModel.initSearch(lastSearch,page);
        page++;
    }

    private void SearchMovies(String query){
        articleArrayList.clear();
        page = 1;
        moviesViewModel.initSearch(query,page);
    }

    private void setupRecyclerView() {
        if (moviesAdapter == null) {
            moviesAdapter = new MovieAdapter(articleArrayList);
            movies_list.setLayoutManager(new GridLayoutManager(getContext(), 2));
            movies_list.setAdapter(moviesAdapter);
            movies_list.setItemAnimator(new DefaultItemAnimator());
            movies_list.setNestedScrollingEnabled(true);
        } else {
            moviesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(((MainActivity) getContext()).getSupportActionBar().getThemedContext());
        // MenuItemCompat.setShowAsAction(item, //MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | //MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        //  MenuItemCompat.setActionView(item, searchView);
        // These lines are deprecated in API 26 use instead
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setActionView(searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                lastSearch = newText;
                SearchMovies(lastSearch);
                return false;
            }
        });
        searchView.setOnClickListener(v -> {

        }
        );
    }
}