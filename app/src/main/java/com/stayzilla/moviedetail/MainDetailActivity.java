package com.stayzilla.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import API.ApiMovie;
import Model.ActorDirector;
import Model.MovieDetail;
import Model.MovieDetailData;
import Utility.Constant;
import Utility.Utils;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainDetailActivity extends AppCompatActivity {

    private static final String TAG = "MainDetailActivity";
    private Context context = MainDetailActivity.this;

    private static final String API = "https://yts.to";

    private LinearLayout loaderLayout;
    private ApiMovie api;

    private String movieId;

    private ImageView moviePoster,movieScreenshot1,movieScreenshot2,movieScreenshot3;
    private LinearLayout releaseYearLayout,ratingLayout,languageLayout,runTimeLayout,categoryLayout,genreLayout,actorLayout,directorLayout,synopsisLayout,
            audienceScoreLayout,criticsScoreLayout,audienceRatingLayout,criticsRatingLayout,mainLayout,downloadLayout,likeLayout;

    private TextView descriptionIntro,year,rating,language,runTime,category,genre,actor,director,synopsis,audienceScore,
            criticsScore,audienceRating,criticsRating,download,like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);

        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        moviePoster = (ImageView) findViewById(R.id.moviePoster);
        descriptionIntro = (TextView) findViewById(R.id.descriptionIntro);

        movieScreenshot1 = (ImageView) findViewById(R.id.movieScreenshot1);
        movieScreenshot2 = (ImageView) findViewById(R.id.movieScreenshot2);
        movieScreenshot3 = (ImageView) findViewById(R.id.movieScreenshot3);

        releaseYearLayout = (LinearLayout) findViewById(R.id.releaseYearLayout);
        year = (TextView) findViewById(R.id.year);
        ratingLayout = (LinearLayout) findViewById(R.id.ratingLayout);
        rating = (TextView) findViewById(R.id.rating);
        languageLayout = (LinearLayout) findViewById(R.id.languageLayout);
        language = (TextView) findViewById(R.id.language);
        runTimeLayout = (LinearLayout) findViewById(R.id.runTimeLayout);
        runTime = (TextView) findViewById(R.id.runTime);
        categoryLayout = (LinearLayout) findViewById(R.id.categoryLayout);
        category = (TextView) findViewById(R.id.category);
        genreLayout = (LinearLayout) findViewById(R.id.genreLayout);
        genre = (TextView) findViewById(R.id.genre);
        actorLayout = (LinearLayout) findViewById(R.id.actorLayout);
        actor = (TextView) findViewById(R.id.actor);
        directorLayout = (LinearLayout) findViewById(R.id.directorLayout);
        director = (TextView) findViewById(R.id.director);
        synopsisLayout = (LinearLayout) findViewById(R.id.synopsisLayout);
        synopsis = (TextView) findViewById(R.id.synopsis);
        audienceScoreLayout = (LinearLayout) findViewById(R.id.audienceScoreLayout);
        audienceScore = (TextView) findViewById(R.id.audienceScore);
        criticsScoreLayout = (LinearLayout) findViewById(R.id.criticsScoreLayout);
        criticsScore = (TextView) findViewById(R.id.criticsScore);
        audienceRatingLayout = (LinearLayout) findViewById(R.id.audienceRatingLayout);
        audienceRating = (TextView) findViewById(R.id.audienceRating);
        criticsRatingLayout = (LinearLayout) findViewById(R.id.criticsRatingLayout);
        criticsRating = (TextView) findViewById(R.id.criticsRating);
        downloadLayout = (LinearLayout) findViewById(R.id.downloadLayout);
        download = (TextView) findViewById(R.id.download);
        likeLayout = (LinearLayout) findViewById(R.id.likeLayout);
        like = (TextView) findViewById(R.id.like);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setNavigationIcon(R.drawable.back_icon);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        if (!ImageLoader.getInstance().isInited()) {
            ImageLoader.getInstance().init(Utils.getImageLoaderConfiguration(context));
        }

        loaderLayout = (LinearLayout) findViewById(R.id.loaderLayout);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API).build();
        api = restAdapter.create(ApiMovie.class);

        Intent recievedIntent = getIntent();

        if (recievedIntent.hasExtra(Constant.MOVIE_ID)){
            movieId = recievedIntent.getExtras().getString(Constant.MOVIE_ID);
            String movieImage = recievedIntent.getExtras().getString(Constant.MOVIE_IMAGE);
            String movieName = recievedIntent.getExtras().getString(Constant.MOVIE_NAME);

            toolbar.setTitle("" + movieName);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));

            showLoader(true);
            mainLayout.setVisibility(View.GONE);


            if (movieImage != null && movieImage.length() > 0) {
                moviePoster.setVisibility(View.VISIBLE);
                moviePoster.setImageResource(R.drawable.loader);
                ImageLoader.getInstance().displayImage(movieImage, moviePoster, Utils.UIL_DEFAULT_DISPLAY_OPTIONS);
            } else {
                moviePoster.setVisibility(View.GONE);
            }

            loadData();
        }

    }

    private void loadData(){

        api.getMovieDetail(movieId,true,true, new Callback<MovieDetailData>() {
            @Override
            public void success(MovieDetailData movieDetailData, Response response) {
                Log.d(TAG, "MainData :" + movieDetailData.getStatus() + " " + movieDetailData.getStatus_message() + " " + movieDetailData.getMovie());
                showData(movieDetailData.getMovie());
                showLoader(false);
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

    private void showData(MovieDetail movie){

        mainLayout.setVisibility(View.VISIBLE);

        String moviePosterUrl = movie.getImages().getLarge_cover_image();
        String movieDesciption = movie.getDescription_intro();
        String movieRating = movie.getRating();
        String movieYear = movie.getYear();
        String movieLanguage = movie.getLanguage();
        String movieRuntime = movie.getRuntime();
        String movieCategory = movie.getMpa_rating();
        String movieSynopsis = movie.getDescription_full();
        String movieAudienceScore = movie.getRt_audience_score();
        String movieAudienceRating = movie.getRt_audience_rating();
        String movieCriticScore = movie.getRt_critics_score();
        String movieCriticRating = movie.getRt_critics_rating();
        String movieDownload = movie.getDownload_count();
        String movieLike = movie.getLike_count();
        List<String> movieGenres = movie.getGenres();
        List<ActorDirector> movieActors = movie.getActors();
        List<ActorDirector> movieDirectors = movie.getDirectors();
        String movieScreenShotUrl1 = movie.getImages().getMedium_screenshot_image1();
        String movieScreenShotUrl2 = movie.getImages().getMedium_screenshot_image2();
        String movieScreenShotUrl3 = movie.getImages().getMedium_screenshot_image3();

        if (movieDesciption != null && !movieDesciption.equals("")) {
            descriptionIntro.setVisibility(View.VISIBLE);
            descriptionIntro.setText("" + movieDesciption);
        } else {
            descriptionIntro.setVisibility(View.GONE);
        }

        if (moviePosterUrl != null && moviePosterUrl.length() > 0) {
            moviePoster.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(moviePosterUrl, moviePoster, Utils.UIL_DEFAULT_DISPLAY_OPTIONS);
        } else {
            moviePoster.setVisibility(View.GONE);
        }

        if (movieScreenShotUrl1 != null && movieScreenShotUrl1.length() > 0) {
            movieScreenshot1.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(movieScreenShotUrl1, movieScreenshot1, Utils.UIL_DEFAULT_DISPLAY_OPTIONS);
        } else {
            movieScreenshot1.setVisibility(View.GONE);
        }

        if (movieScreenShotUrl2 != null && movieScreenShotUrl2.length() > 0) {
            movieScreenshot2.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(movieScreenShotUrl2, movieScreenshot2, Utils.UIL_DEFAULT_DISPLAY_OPTIONS);
        } else {
            movieScreenshot2.setVisibility(View.GONE);
        }

        if (movieScreenShotUrl3 != null && movieScreenShotUrl3.length() > 0) {
            movieScreenshot3.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(movieScreenShotUrl3, movieScreenshot3, Utils.UIL_DEFAULT_DISPLAY_OPTIONS);
        } else {
            movieScreenshot3.setVisibility(View.GONE);
        }

        if (movieYear != null && !movieYear.equals("")) {
            releaseYearLayout.setVisibility(View.VISIBLE);
            year.setText("" + movieYear);
        } else {
            releaseYearLayout.setVisibility(View.GONE);
        }

        if (movieRating != null && !movieRating.equals("")) {
            ratingLayout.setVisibility(View.VISIBLE);
            rating.setText("" + movieRating);
        } else {
            ratingLayout.setVisibility(View.GONE);
        }

        if (movieLanguage != null && !movieLanguage.equals("")) {
            languageLayout.setVisibility(View.VISIBLE);
            language.setText("" + movieLanguage);
        } else {
            languageLayout.setVisibility(View.GONE);
        }

        if (movieRuntime != null && !movieRuntime.equals("")) {
            runTimeLayout.setVisibility(View.VISIBLE);
            runTime.setText("" + movieRuntime + " mins");
        } else {
            runTimeLayout.setVisibility(View.GONE);
        }

        if (movieCategory != null && !movieCategory.equals("")) {
            categoryLayout.setVisibility(View.VISIBLE);
            category.setText("" + movieCategory);
        } else {
            categoryLayout.setVisibility(View.GONE);
        }

        if (movieSynopsis != null && !movieSynopsis.equals("")) {
            synopsisLayout.setVisibility(View.VISIBLE);
            synopsis.setText("" + movieSynopsis);
        } else {
            synopsisLayout.setVisibility(View.GONE);
        }

        if (movieAudienceScore != null && !movieAudienceScore.equals("")) {
            audienceScoreLayout.setVisibility(View.VISIBLE);
            audienceScore.setText("" + movieAudienceScore);
        } else {
            audienceScoreLayout.setVisibility(View.GONE);
        }

        if (movieAudienceRating != null && !movieAudienceRating.equals("")) {
            audienceRatingLayout.setVisibility(View.VISIBLE);
            audienceRating.setText("" + movieAudienceRating);
        } else {
            audienceRatingLayout.setVisibility(View.GONE);
        }

        if (movieCriticScore != null && !movieCriticScore.equals("")) {
            criticsScoreLayout.setVisibility(View.VISIBLE);
            criticsScore.setText("" + movieCriticScore);
        } else {
            criticsScoreLayout.setVisibility(View.GONE);
        }

        if (movieCriticRating != null && !movieCriticRating.equals("")) {
            criticsRatingLayout.setVisibility(View.VISIBLE);
            criticsRating.setText("" + movieCriticRating);
        } else {
            criticsRatingLayout.setVisibility(View.GONE);
        }

        if (movieDownload != null && !movieDownload.equals("")) {
            downloadLayout.setVisibility(View.VISIBLE);
            download.setText("" + movieDownload);
        } else {
            downloadLayout.setVisibility(View.GONE);
        }

        if (movieLike != null && !movieLike.equals("")) {
            likeLayout.setVisibility(View.VISIBLE);
            like.setText("" + movieLike);
        } else {
            likeLayout.setVisibility(View.GONE);
        }

        String genreString = "";
        if(movieGenres.size() > 0){
            for(int i = 0 ; i < movieGenres.size() ; i++){
                if(i == 0){
                    genreString = genreString +  movieGenres.get(i);
                } else {
                    genreString = genreString +  " , " + movieGenres.get(i);
                }
            }
            genreLayout.setVisibility(View.VISIBLE);
            genre.setText("" + genreString);
        } else {
            genreLayout.setVisibility(View.GONE);
        }

        String actorString = "";
        if(movieActors.size() > 0){
            for(int i = 0 ; i < movieActors.size() ; i++){
                if(i == 0){
                    actorString = actorString +  movieActors.get(i).getName();
                } else {
                    actorString = actorString +  " , " + movieActors.get(i).getName();
                }
            }
            actorLayout.setVisibility(View.VISIBLE);
            actor.setText("" + actorString);
        } else {
            actorLayout.setVisibility(View.GONE);
        }

        String directorString = "";
        if(movieDirectors.size() > 0){
            for(int i = 0 ; i < movieDirectors.size() ; i++){
                if(i == 0){
                    directorString = directorString +  movieDirectors.get(i).getName();
                } else {
                    directorString = directorString +  " , " + movieDirectors.get(i).getName();
                }
            }
            directorLayout.setVisibility(View.VISIBLE);
            director.setText("" + directorString);
        } else {
            directorLayout.setVisibility(View.GONE);
        }

    }

    private void showLoader(boolean show) {
        if (show) {
            loaderLayout.setVisibility(View.VISIBLE);
        } else {
            loaderLayout.setVisibility(View.GONE);
        }
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

    @Override
    public void onBackPressed() {
        Log.d(TAG, "back press");
        this.finish();
        super.onBackPressed();
    }
}
