package com.example.movieappv1.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.movieappv1.models.Movie;
import com.example.movieappv1.models.MovieDetail;


import java.util.List;
@Dao
public interface MovieDao {
    @Query("SELECT * from movie")
    public List<Movie> getAll();

    @Query("SELECT * from movie")
    public LiveData<List<Movie>> getAllAsync();

  /*  @Query("SELECT * from details")
    public LiveData<MovieDetail> getMovieAsync();*/

    @Query("SELECT * from movie WHERE custom_title LIKE :title")
    public List<Movie> findByTitle(String title); // title == "abc"


    @Query("SELECT * from movie WHERE imdbID LIKE :id")
    public MovieDetail findById(String id); // title == "abc"


    @Insert
    public void insert(Movie ...movies);


  /*  @Insert
    public void insert2(MovieDetail ...movies);*/

    @Query("DELETE from movie WHERE imdbID = :id")
    public void delete(String id);

/*
    @Query("DELETE from details")
    public void delete2();*/


    @Query("DELETE from movie")
    public void deleteAll();

}
