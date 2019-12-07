package com.example.movieappv1.db;


import android.content.Context;
import android.content.ServiceConnection;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.example.movieappv1.models.Movie;
import com.example.movieappv1.models.MovieDetail;

import java.io.Serializable;
import java.util.List;


public class Repository implements Serializable {

    private static MovieDatabase database = null;

    public Repository(Context context)
    {
        if(database==null)
        {
            database = Room.databaseBuilder(context, MovieDatabase.class, "db-app")
                    .fallbackToDestructiveMigration().build();
        }
    }
    public LiveData<List<Movie>> getAllMovies() {
        return database.getMovieDao().getAllAsync();
    }
/*
    public LiveData<MovieDetail> getMovie() {
        return database.getMovieDao().getMovieAsync();
    }*/

    public void insert(final Movie movie1) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                final Movie movie = movie1;
                database.getMovieDao().insert(movie1);
                return null;
            }
        }.execute();
    }

    public void deleteAll() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.getMovieDao().deleteAll();
                return null;
            }
        }.execute();

    }

  /*  public void insert2(final MovieDetail movieDetail) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.getMovieDao().insert2(movieDetail);
                return null;
            }
        }.execute();*/

    }
 /*   public void delete() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                database.getMovieDao().delete2();
                return null;
            }
        }.execute();

    }*/


