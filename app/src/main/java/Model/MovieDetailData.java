package Model;

/**
 * Created by karangarg on 26/09/15.
 */
public class MovieDetailData {

    private String status;
    private String status_message;
    private MovieDetail data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public MovieDetail getMovie() {
        return data;
    }

    public void setMovie(MovieDetail movie) {
        this.data = movie;
    }
}