package com.example.sonukg.movieapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sonukg.movieapp.CustomItemClickListener;
import com.example.sonukg.movieapp.R;
import com.example.sonukg.movieapp.model.Result;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private Context context;
    private ArrayList<Result> results=null;
    CustomItemClickListener listener;

    public MoviesAdapter(Context context, ArrayList<Result> results,CustomItemClickListener listener) {
        this.context = context;
        this.results = results;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_items,parent,
                false);
        final MoviesViewHolder moviesViewHolder=new MoviesViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,moviesViewHolder.getPosition());
            }
        });
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
//        holder.movie_Image.setImageResource(Integer.parseInt(results.get(position).getPosterPath
//                ()));
        holder.movie_title.setText(results.get(position).getOriginalTitle());
        holder.release_date.setText(results.get(position).getReleaseDate());
        holder.movie_adult.setText(results.get(position).getAdult().toString());
        String imagePath="https://image.tmdb.org/t/p/original"+results.get(position)
                .getPosterPath();
        Glide.with(context)
                .load(imagePath)
                .into(holder.movie_Image);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        ImageView movie_Image;
        TextView movie_title;
        TextView release_date;
        TextView movie_adult;
        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_Image=itemView.findViewById(R.id.item_movie_image);
            movie_title=itemView.findViewById(R.id.item_movie_title);
            release_date=itemView.findViewById(R.id.item_movie_release_date);
            movie_adult=itemView.findViewById(R.id.item_movie_adult);
        }
    }
}
