package com.example.movieappv1.services;

import com.example.movieappv1.models.MovieDetail;
import com.example.movieappv1.models.MovieList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieListService {
    @GET("?type=movie")
    Call<MovieList> getMovies(
            @Query("apikey") String apiKey,
            @Query("s") String title);


    @GET("?type=movie$plot=short")
    Call<MovieDetail> getMovie(
            @Query("apikey") String apiKey,
            @Query("i") String id);
}
