package Model;

/**
 * Created by karangarg on 26/09/15.
 */
public class Movie {

    private String medium_cover_image;
    private String id;
    private String title;
    private String year;
    private String rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedium_cover_image() {
        return medium_cover_image;
    }

    public void setMedium_cover_image(String medium_cover_image) {
        this.medium_cover_image = medium_cover_image;
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
}
