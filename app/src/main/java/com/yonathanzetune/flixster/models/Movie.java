package com.yonathanzetune.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {


    String posterPath;
    String backdropPath;
    String title;
    String overview;

    public Movie(JSONObject json) throws JSONException {
        posterPath = json.getString("backdrop_path");
        backdropPath = json.getString("poster_path");
        title = json.getString("title");
        overview = json.getString("overview");

    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); i++) {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));

        }
        return movies;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getOverview() {
        return overview;
    }
}
