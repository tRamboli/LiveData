package com.spartapps.livedata.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.spartapps.livedata.Movie;
import com.spartapps.livedata.R;
import com.spartapps.livedata.http.MovieClientInstance;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final List<Movie> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final TextView year;
        private final TextView title;
        private final TextView rating;
        private final ImageView poster;

        public MovieViewHolder(View view) {
            super(view);
            year = view.findViewById(R.id.year);
            title = view.findViewById(R.id.title);
            rating = view.findViewById(R.id.rating);
            poster = view.findViewById(R.id.poster);
        }

        public void SetMovieData(Movie movie){
            year.setText(movie.getRelease_date());
            title.setText(movie.getTitle());
            rating.setText(String.valueOf(movie.getVote_average()));
            Glide.with(itemView.getContext()).load(MovieClientInstance.photo_url+movie.getPoster_path()).placeholder(R.mipmap.ic_launcher).into(poster);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MovieAdapter(List<Movie> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.SetMovieData(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}