package com.example.nguyhuy.msl;

import android.os.AsyncTask;
import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch details on Game of Thrones
                String gameOfThrones = "https://api.themoviedb.org/3/tv/1399?api_key=35d25d93d8eb4b186de3b9759338f7a9";
                new RetrieveRestResponse().execute(gameOfThrones);

                // Get a list of the current popular TV shows on TMDb. This list updates daily.
                String popular = "https://api.themoviedb.org/3/tv/popular?page=1&language=en-US&api_key=35d25d93d8eb4b186de3b9759338f7a9";
                //new RetrieveRestResponse().execute(popular);

                // Get a list of the top rated TV shows on TMDb.
                String topRated = "https://api.themoviedb.org/3/tv/top_rated?page=1&language=en-US&api_key=35d25d93d8eb4b186de3b9759338f7a9";
                //new RetrieveRestResponse().execute(topRated);

                // Search for a TV show.
                // https://developers.themoviedb.org/3/search/search-tv-shows
                String search = "https://api.themoviedb.org/3/search/tv?page=1&language=en-US&api_key=35d25d93d8eb4b186de3b9759338f7a9";
            }
        });
    }
}