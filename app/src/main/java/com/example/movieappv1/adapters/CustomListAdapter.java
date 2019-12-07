package com.example.movieappv1.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieappv1.R;
import com.example.movieappv1.holders.CustomListViewHolder;
import com.example.movieappv1.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter {

    List<Movie> dataset;
    View.OnClickListener listener;

    public CustomListAdapter(View.OnClickListener listener){

        this.dataset = new ArrayList<>();
        this.listener = listener;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new CustomListViewHolder(view,parent.getContext());



    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((CustomListViewHolder) holder).setText(dataset.get(position).Title, dataset.get(position).Year, dataset.get(position).Poster, listener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void updateDataset(List<Movie> newDataset){
        this.dataset = newDataset;
        notifyDataSetChanged();
    }
    public String getClickedItemId(CustomListViewHolder holder) {
        int adapterPosition = holder.getAdapterPosition();
        return dataset.get(adapterPosition).imdbID;
    }
}
