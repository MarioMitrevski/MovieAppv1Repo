package com.example.movieappv1.holders;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieappv1.R;
import com.squareup.picasso.Picasso;


public class CustomListViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textView;
    private TextView movieYear;
    Context context;
    public CustomListViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageItemView1);
        textView = itemView.findViewById(R.id.textItemView1);
        movieYear = itemView.findViewById(R.id.textView);
        itemView.setTag(this);
        this.context=context;

    }

    public void setText(String title, String year, String image,View.OnClickListener listener) {
        textView.setText(title);

        Picasso.with(context)
                .load(image)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(imageView);
        movieYear.setText(year);
        itemView.setOnClickListener(listener);

    }
}