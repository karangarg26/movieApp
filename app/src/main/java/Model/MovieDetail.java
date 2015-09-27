package Model;

import java.util.List;

/**
 * Created by karangarg on 26/09/15.
 */
public class MovieDetail {

    private String id;
    private String title;
    private String year;
    private String rating;
    private String runtime;
    private String language;
    private String mpa_rating;
    private String download_count;
    private String like_count;
    private String rt_audience_score;
    private String rt_critics_rating;
    private String rt_audience_rating;
    private String rt_critics_score;
    private String description_full;
    private String description_intro;
    private List<String> genres;
    private List<ActorDirector> actors;
    private List<ActorDirector> directors;
    private Image images;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMpa_rating() {
        return mpa_rating;
    }

    public void setMpa_rating(String mpa_rating) {
        this.mpa_rating = mpa_rating;
    }

    public String getDownload_count() {
        return download_count;
    }

    public void setDownload_count(String download_count) {
        this.download_count = download_count;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getRt_audience_score() {
        return rt_audience_score;
    }

    public void setRt_audience_score(String rt_audience_score) {
        this.rt_audience_score = rt_audience_score;
    }

    public String getRt_critics_rating() {
        return rt_critics_rating;
    }

    public void setRt_critics_rating(String rt_critics_rating) {
        this.rt_critics_rating = rt_critics_rating;
    }

    public String getRt_audience_rating() {
        return rt_audience_rating;
    }

    public void setRt_audience_rating(String rt_audience_rating) {
        this.rt_audience_rating = rt_audience_rating;
    }

    public String getRt_critics_score() {
        return rt_critics_score;
    }

    public void setRt_critics_score(String rt_critics_score) {
        this.rt_critics_score = rt_critics_score;
    }

    public String getDescription_full() {
        return description_full;
    }

    public void setDescription_full(String description_full) {
        this.description_full = description_full;
    }

    public String getDescription_intro() {
        return description_intro;
    }

    public void setDescription_intro(String description_intro) {
        this.description_intro = description_intro;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<ActorDirector> getActors() {
        return actors;
    }

    public void setActors(List<ActorDirector> actors) {
        this.actors = actors;
    }

    public List<ActorDirector> getDirectors() {
        return directors;
    }

    public void setDirectors(List<ActorDirector> directors) {
        this.directors = directors;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }
}
