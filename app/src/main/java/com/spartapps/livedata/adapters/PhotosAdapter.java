package com.spartapps.livedata.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.spartapps.livedata.Photo;
import com.spartapps.livedata.R;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {
    private final List<Photo> mDataset;
    private static int i=0;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class PhotosViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private final TextView id;
        private final TextView title;
        private final ImageView photo_img;

        public PhotosViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.id);
            title = view.findViewById(R.id.title);
            photo_img = view.findViewById(R.id.photo);
        }

        public void SetPhotoData(Photo photo){
            id.setText(String.valueOf(photo.getId()));
            title.setText(photo.getTitle());
            Glide.with(itemView.getContext()).load("https://i.picsum.photos/id/427/1000/1000.jpg").apply(new RequestOptions().signature(new ObjectKey(""+i))).into(photo_img);
            i++;

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PhotosAdapter(List<Photo> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PhotosAdapter.PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new PhotosViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(PhotosViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.SetPhotoData(mDataset.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}