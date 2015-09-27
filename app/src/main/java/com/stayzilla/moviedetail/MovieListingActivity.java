package com.stayzilla.moviedetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import API.ApiMovie;
import Adapter.MovieAdapter;
import Model.MainData;
import Model.Movie;
import Utility.Utils;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MovieListingActivity extends AppCompatActivity {

    private static final String TAG = "MovieListingActivity";
    private Context context = MovieListingActivity.this;

    private static final String API = "https://yts.to";

    private Toolbar toolbar;
    private RecyclerView recList;
    private LinearLayout loaderLayout;
    private TextView loaderTextView;

    private List<Movie> moviesList;
    private MovieAdapter adapter;
    private RestAdapter restAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ApiMovie api;

    private int page = 1;

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 2;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_listing);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.main_title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        ImageLoader.getInstance().init(Utils.getImageLoaderConfiguration(context));

        recList = (RecyclerView) findViewById(R.id.cardList);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(linearLayoutManager);

        loaderLayout = (LinearLayout) findViewById(R.id.loaderLayout);
        loaderTextView = (TextView) findViewById(R.id.loaderTextView);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();
        api = restAdapter.create(ApiMovie.class);

        moviesList = new ArrayList<>();

        adapter = new MovieAdapter(context, moviesList);
        recList.setAdapter(adapter);

        loadData();

        recList.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                Log.d(TAG,"dy :" + dy);

                if(dy < 0){
                    showLoader(false);
                }

                visibleItemCount = recList.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    loadData();
                    loading = true;
                }
            }
        });

    }

    private void loadData(){

        if(page == 1){
            loaderTextView.setText(R.string.default_loader);
        } else {
            loaderTextView.setText(R.string.more_loading);
        }

        showLoader(true);

        api.getData("rating", String.valueOf(30), String.valueOf(page), new Callback<MainData>() {
            @Override
            public void success(MainData mainData, Response response) {
                Log.d(TAG, "MainData :" + mainData.getStatus() + " " + mainData.getStatus_message() + " " + mainData.getData());
                List<Movie> movies = mainData.getData().getMovies();
                moviesList.addAll(moviesList.size(),movies);
                adapter.notifyDataSetChanged();
                showLoader(false);
                page++;
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :" + error.getMessage());
                Toast.makeText(context, "Slow Internet Connection", Toast.LENGTH_SHORT).show();
                showLoader(false);

                loadData();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    private void showLoader(boolean show) {
        if (show) {
            loaderLayout.setVisibility(View.VISIBLE);
        } else {
            loaderLayout.setVisibility(View.GONE);
        }
    }
}
