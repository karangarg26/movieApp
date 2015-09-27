package Model;

import java.util.List;

/**
 * Created by karangarg on 26/09/15.
 */
public class MovieData {
    private String movie_count;
    private String page_number;
    private String limit;
    private List<Movie> movies;

    public String getMovie_count() {
        return movie_count;
    }

    public void setMovie_count(String movie_count) {
        this.movie_count = movie_count;
    }

    public String getPage_number() {
        return page_number;
    }

    public void setPage_number(String page_number) {
        this.page_number = page_number;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
