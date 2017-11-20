package com.example.nguyhuy.myserielist;

import android.os.NetworkOnMainThreadException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // final private String API_KEY = "35d25d93d8eb4b186de3b9759338f7a9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button test = (Button) findViewById(R.id.button);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = "https://api.themoviedb.org/3/tv/1399?api_key=35d25d93d8eb4b186de3b9759338f7a9";

                OkHttpClient client = new OkHttpClient();

                MediaType mediaType = MediaType.parse("application/octet-stream");
                RequestBody body = RequestBody.create(mediaType, "{}");
                Request request = new Request.Builder()
                /*.url("https://api.themoviedb.org/3/find/%7Bexternal_id%7D?external_source=imdb_id&language=en-US&api_key=%3C%3Capi_key%3E%3E")*/
                        .url(test)
                        .get()
                        .build();

                // https://stackoverflow.com/questions/6343166/how-do-i-fix-android-os-networkonmainthreadexception

                try {
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {

                } catch (NetworkOnMainThreadException e) {
                    System.out.print("erreur attendue " + e.getMessage());
                }
            }
        });
    }
}
