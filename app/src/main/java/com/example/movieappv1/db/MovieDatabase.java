package com.example.movieappv1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.movieappv1.models.Movie;
import com.example.movieappv1.models.MovieDetail;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();

}
