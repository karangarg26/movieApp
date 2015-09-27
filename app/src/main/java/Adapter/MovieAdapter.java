package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.stayzilla.moviedetail.MainDetailActivity;
import com.stayzilla.moviedetail.R;

import java.util.List;

import Model.Movie;
import Utility.Constant;
import Utility.Utils;

/**
 * Created by karangarg on 26/09/15.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieCardHolder> {

    private List<Movie> moviesList;
    private Context mContext;

    public MovieAdapter(Context context, List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.mContext = context;
    }

    @Override
    public MovieCardHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_layout, viewGroup, false);
        return new MovieCardHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieCardHolder movieViewHolder, int i) {
        Movie movie = moviesList.get(i);

        //Setting Data
        String moviePosterUrl = movie.getMedium_cover_image();
        String movieName = movie.getTitle();
        String movieRating = movie.getRating();
        String movieYear = movie.getYear();

        if (moviePosterUrl != null && moviePosterUrl.length() > 0) {
            movieViewHolder.moviePoster.setVisibility(View.VISIBLE);
            movieViewHolder.moviePoster.setImageResource(R.drawable.loader);
            ImageLoader.getInstance().displayImage(moviePosterUrl, movieViewHolder.moviePoster, Utils.UIL_DEFAULT_DISPLAY_OPTIONS);
        } else {
            movieViewHolder.moviePoster.setVisibility(View.GONE);
        }

        if (movieName != null && !movieName.equals("")) {
            movieViewHolder.movieName.setVisibility(View.VISIBLE);
            movieViewHolder.movieName.setText("" + movieName);
        } else {
            movieViewHolder.movieName.setVisibility(View.GONE);
        }

        if (movieRating != null && !movieRating.equals("")) {
            movieViewHolder.movieRating.setVisibility(View.VISIBLE);
            movieViewHolder.movieRating.setText("Rating : " + movieRating);
        } else {
            movieViewHolder.movieRating.setVisibility(View.GONE);
        }

        if (movieYear != null && !movieYear.equals("")) {
            movieViewHolder.movieReleasedYear.setVisibility(View.VISIBLE);
            movieViewHolder.movieReleasedYear.setText("Released Year : " + movieYear);
        } else {
            movieViewHolder.movieReleasedYear.setVisibility(View.GONE);
        }

        movieViewHolder.cardLayout.setTag(movieViewHolder);
        movieViewHolder.cardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieCardHolder holder = (MovieCardHolder) view.getTag();
                int position = holder.getPosition();

                Movie movie = moviesList.get(position);
                String movieId = movie.getId();
                String moviePosterUrl = movie.getMedium_cover_image();
                String movieName = movie.getTitle();
                String movieYear = movie.getYear();

                Intent intent = new Intent(mContext,MainDetailActivity.class);
                intent.putExtra(Constant.MOVIE_ID,movieId);
                intent.putExtra(Constant.MOVIE_IMAGE,moviePosterUrl);
                intent.putExtra(Constant.MOVIE_NAME,movieName);
                intent.putExtra(Constant.MOVIE_YEAR,movieYear);

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != moviesList ? moviesList.size() : 0);
    }

    public static class MovieCardHolder extends RecyclerView.ViewHolder {

        protected ImageView moviePoster;
        protected TextView movieName;
        protected TextView movieReleasedYear;
        protected TextView movieRating;
        protected LinearLayout cardLayout;

        public MovieCardHolder(View view) {
            super(view);
            this.cardLayout = (LinearLayout) view.findViewById(R.id.cardLayout);
            this.moviePoster = (ImageView) view.findViewById(R.id.moviePoster);
            this.movieName = (TextView) view.findViewById(R.id.movieName);
            this.movieReleasedYear = (TextView) view.findViewById(R.id.movieReleasedYear);
            this.movieRating = (TextView) view.findViewById(R.id.movieRating);
        }

    }

}
