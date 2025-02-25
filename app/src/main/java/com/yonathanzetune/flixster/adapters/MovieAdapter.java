package com.yonathanzetune.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yonathanzetune.flixster.R;
import com.yonathanzetune.flixster.VideoActivity;
import com.yonathanzetune.flixster.models.Movie;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    TextView titleTV;
    TextView overviewTV;
    ImageView posterImgV;
    ImageView playButton;

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.titleTV);
            overviewTV = itemView.findViewById(R.id.overviewTV);
            posterImgV = itemView.findViewById(R.id.posterId);
            playButton = itemView.findViewById(R.id.playButton);
        }

        public void bind(final Movie mMovie) {
            titleTV.setText(mMovie.getTitle());
            overviewTV.setText(mMovie.getOverview());
            playButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra(VideoActivity.VIDEO_ID, String.valueOf(mMovie.getId()));
                    context.startActivity(intent);

                }
            });

            String imgURL;//

            if (context.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
                imgURL = mMovie.getBackdropPath();

            } else {
                imgURL = mMovie.getPosterPath();
            }
            Glide.with(context).load(imgURL).transform(new RoundedCornersTransformation(50, 10))
                    .into(posterImgV);
        }
    }
}
