package com.yonathanzetune.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.yonathanzetune.flixster.adapters.MovieAdapter;
import com.yonathanzetune.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    public static final String TAG = "MainActivity";

    List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AsyncHttpClient client = new AsyncHttpClient();
        movies = new ArrayList<>();

        RecyclerView moviesRv = findViewById(R.id.moviesRV);


        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        moviesRv.setAdapter(movieAdapter);

        moviesRv.setLayoutManager(new LinearLayoutManager(this));



        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
//                Log.d("JSONRES", json.toString());
                JSONObject mJson = json.jsonObject;
                try {
                    JSONArray results = mJson.getJSONArray("results");
                    movies.addAll(Movie.fromJsonArray(results));
                    movieAdapter.notifyDataSetChanged();
                    Log.d("MOVIES", movies.toString());

                } catch (JSONException e) {
                    Log.e(TAG, "JSON exception getting results", e);

                }


            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });

    }
}