package com.yonathanzetune.flixster;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.yonathanzetune.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;

public class VideoActivity extends YouTubeBaseActivity {

    public static final String VIDEO_ID = "VIDEO_ID";
    public static final String TAG = "VideoActivity";
    public static final String GET_VIDEO_URL = "https://api.themoviedb.org/3/movie/%s/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";


    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);
        Log.d(TAG, "ONCREATE");
        youTubePlayerView =
                (YouTubePlayerView) findViewById(R.id.youtubePlayer);
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(String.format(GET_VIDEO_URL, getIntent().getStringExtra(VIDEO_ID)), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d("JSONRES", json.toString());
                JSONObject mJson = json.jsonObject;
                try {
                    JSONArray results = mJson.getJSONArray("results");
                    if (results.length() > 0) {
                        JSONObject videoObj = results.getJSONObject(0);
                        final String videoURL = videoObj.getString("key");
                        youTubePlayerView.initialize("AIzaSyAY3L7FuWsv3faXvQC4O0fxAnNUzBoZbqM",
                                new YouTubePlayer.OnInitializedListener() {
                                    @Override
                                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                        YouTubePlayer youTubePlayer, boolean b) {

                                        Log.d(TAG, "VIDEO INTIALIZED");
                                        // do any work here to cue video, play video, etc.
                                        youTubePlayer.cueVideo(videoURL);
                                    }

                                    @Override
                                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                        YouTubeInitializationResult youTubeInitializationResult) {
                                        Log.d(TAG, "VIDEO FAILED");

                                    }
                                });

                    }

                } catch (JSONException e) {
                    Log.e(TAG, "JSON exception getting results", e);

                }


            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {

            }
        });


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
