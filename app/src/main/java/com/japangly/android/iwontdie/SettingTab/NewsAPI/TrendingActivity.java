package com.japangly.android.iwontdie.SettingTab.NewsAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.japangly.android.iwontdie.MainActivity;
import com.japangly.android.iwontdie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class TrendingActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://newsapi.org/v2/top-headlines?sources=the-verge&apiKey=69c4d843508a45db8ccbe3a292a79b2c";
    private ArrayList<TrendingCustomClass> trendingArticles;
    private TrendingAdapter trendingAdapter;
    private ListView listView;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        progressbar = findViewById(R.id.progress_bar);

        listView = findViewById(R.id.list_item);

        trendingArticles = new ArrayList<>();

        fetchJsonData();
    }

    private void fetchJsonData() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressbar.setVisibility(View.INVISIBLE);

                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("articles");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject json = jsonArray.getJSONObject(i);
                                TrendingCustomClass jsondata = new TrendingCustomClass(
                                        json.getString("author"),
                                        json.getString("title"),
                                        json.getString("description"),
                                        json.getString("url"),
                                        json.getString("urlToImage"),
                                        json.getString("publishedAt")
                                );

                                trendingArticles.add(jsondata);
                            }

                            trendingAdapter = new TrendingAdapter(TrendingActivity.this, trendingArticles);
                            listView.setAdapter(trendingAdapter);

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Error Occurred", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            startActivity(new Intent(getApplication(), MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
