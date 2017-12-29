package com.example.nguyhuy.msl;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RetrieveRestResponse extends AsyncTask<String, Void, String> {
    final private String API_KEY = "35d25d93d8eb4b186de3b9759338f7a9";
    private Exception exception;

    protected String doInBackground(String... urls) {
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/octet-stream");
            Request request = new Request.Builder()
                    .url(urls[0])
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(String result) {
        try {
            JSONObject jObject = new JSONObject(result);
            final String path = "http://image.tmdb.org/t/p/w185";
            Serie serie = new Serie();
            JSONArray seasonJson = jObject.getJSONArray("seasons");
            JSONObject lastObj = seasonJson.getJSONObject(seasonJson.length() - 1);

            serie.setImage(path + lastObj.getString("poster_path"));
            serie.setTitle(jObject.getString("name"));
            serie.setTmdb_score(jObject.getDouble("vote_average"));
            /*
            serie.setMax_episode();
            serie.setMax_season(lastObj.getInt("season_number"));*/

            ArrayList<Season> seasons = new ArrayList<>();

            for (int i = 0; i < seasonJson.length(); ++i) {
                JSONObject jsonObject = seasonJson.getJSONObject(i);
                Season season = new Season();
                season.setEpisode_count(jsonObject.getInt("episode_count"));
                season.setPoster_path(path + jsonObject.getString("poster_path"));
                season.setSeason_number(jsonObject.getInt("season_number"));
                seasons.add(season);
            }

            serie.setSeasons(seasons);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
