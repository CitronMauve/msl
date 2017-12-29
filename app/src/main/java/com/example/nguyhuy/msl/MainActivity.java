package com.example.nguyhuy.msl;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    Serie[] series;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Toutes les séries");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Toutes les séries");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("En cours");
        spec.setContent(R.id.tab2);
        spec.setIndicator("En cours");
        host.addTab(spec);

        String popular = "https://api.themoviedb.org/3/tv/popular?page=1&language=en-US&api_key=35d25d93d8eb4b186de3b9759338f7a9";

        AsyncTask task = new RetrieveRestResponse().execute(popular);

        String result = "";
        try {
            result = task.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        try {
            assert jsonObject != null;
            jsonArray =  jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Serie[] series = new Serie[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject array = null;
            try {
                array = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Serie serie = new Serie();
            try {
                assert array != null;
                serie.setImage((String) array.get("poster_path"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                serie.setTitle((String) array.get("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            serie.setUser_score(0);
            try {
                serie.setTmdb_score(array.getDouble("vote_average"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                serie.setDescription(array.getString("overview"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            series[i] = serie;
        }

        TableLayout tableLayout = (TableLayout) findViewById(R.id.toutes_series);
        for (int j = 0; j < series.length; j++) {


            TableRow tableRow = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(lp);
            ImageView imageView = new ImageView(this);

            AsyncTask image = new RetrieveImage().execute(series[j].getImage());

            Bitmap b = null;
            try {
                b = (Bitmap) image.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            assert b != null;
            b = Bitmap.createScaledBitmap(b, 200, 200, false);

            imageView.setImageBitmap(b);

            TextView title = new TextView(this);
            title.setText(series[j].getTitle());
            title.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

            TextView score_tmdb = new TextView(this);
            score_tmdb.setText("Note : " + series[j].getTmdb_score() + "/10");
            score_tmdb.setGravity(Gravity.END);

            tableRow.addView(imageView);
            tableRow.addView(title);
            tableRow.addView(score_tmdb);
            if (j % 2 == 0) {
                tableRow.setBackgroundColor(getResources().getColor(R.color.grey));
            }
            tableLayout.addView(tableRow);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.toutes_series);
        for (int k = 0; k < tableLayout.getChildCount(); k++) {
            TableRow row = (TableRow) tableLayout.getChildAt(k);
            final int finalK = k;

            final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

            row.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    alertBuilder.setMessage(series[finalK].getDescription())
                            .setPositiveButton("Ajouter à mes séries", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    System.out.println("ajouté");
                                }
                            })
                            .setNegativeButton("Fermer", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    System.out.println("fermé");
                                }
                            });
                    alertBuilder.create();
                }

            });
            alertBuilder.show();
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        final Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Fetch details on Game of Thrones
//                String gameOfThrones = "https://api.themoviedb.org/3/tv/1399?api_key=35d25d93d8eb4b186de3b9759338f7a9";
//                AsyncTask task = new RetrieveRestResponse().execute(gameOfThrones);
//
//                String plop = "";
//                try {
//                    plop = task.get().toString();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println(plop);
//
//                // Get a list of the current popular TV shows on TMDb. This list updates daily.
//                String popular = "https://api.themoviedb.org/3/tv/popular?page=1&language=en-US&api_key=35d25d93d8eb4b186de3b9759338f7a9";
//                //new RetrieveRestResponse().execute(popular);
//
//                // Get a list of the top rated TV shows on TMDb.
//                String topRated = "https://api.themoviedb.org/3/tv/top_rated?page=1&language=en-US&api_key=35d25d93d8eb4b186de3b9759338f7a9";
//                //new RetrieveRestResponse().execute(topRated);
//
//                // Search for a TV show.
//                // https://developers.themoviedb.org/3/search/search-tv-shows
//                String search = "https://api.themoviedb.org/3/search/tv?page=1&language=en-US&api_key=35d25d93d8eb4b186de3b9759338f7a9";
//                final TextView textView = (TextView) findViewById(R.id.textView);
//            }
//        });
//    }
}