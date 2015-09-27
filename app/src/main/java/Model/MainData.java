package Model;

/**
 * Created by karangarg on 26/09/15.
 */
public class MainData {

    private String status;
    private String status_message;
    private MovieData data;

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

    public MovieData getData() {
        return data;
    }

    public void setData(MovieData data) {
        this.data = data;
    }
}
