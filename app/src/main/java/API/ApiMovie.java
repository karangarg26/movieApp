package API;

import Model.MainData;
import Model.MovieDetailData;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by karangarg on 26/09/15.
 */
public interface ApiMovie {

    @GET("/api/v2/list_movies.json")
    public void getData(@Query("sort_by") String sort,@Query("limit") String limit,@Query("page") String page,Callback<MainData> response);

    @GET("/api/v2/movie_details.json")
    public void getMovieDetail(@Query("movie_id") String movie_id,@Query("with_images") Boolean with_images,@Query("with_cast") Boolean with_cast,Callback<MovieDetailData> response);

}
