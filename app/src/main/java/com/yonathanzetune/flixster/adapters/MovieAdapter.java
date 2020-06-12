package com.yonathanzetune.flixster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yonathanzetune.flixster.R;
import com.yonathanzetune.flixster.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    TextView titleTV;
    TextView overviewTV;
    ImageView posterImgV;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie mMovie = movies.get(position);
        holder.bind(mMovie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            overviewTV = itemView.findViewById(R.id.overviewTV);
            posterImgV = itemView.findViewById(R.id.posterId);
        }

        public void bind(Movie mMovie) {
            titleTV.setText(mMovie.getTitle());
            overviewTV.setText(mMovie.getOverview());
            Glide.with(context).load(mMovie.getPosterPath()).into(posterImgV);
        }
    }
}
