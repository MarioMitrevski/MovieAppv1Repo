package com.example.movieappv1.asynctask;


import android.os.AsyncTask;
import com.example.movieappv1.MovieListInterface;
import com.example.movieappv1.client.OMDBApiClient;
import com.example.movieappv1.db.Repository;
import com.example.movieappv1.models.Movie;
import com.example.movieappv1.models.MovieList;
import retrofit2.Call;

import java.io.IOException;

public class MovieListSyncTask extends AsyncTask<String,Integer, MovieList> {

    Repository repository;

    public MovieListSyncTask(Repository repository){
        this.repository = repository;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        repository.deleteAll();
    }
    @Override
    protected MovieList doInBackground(String... strings) {
        final Call<MovieList> call = OMDBApiClient.getService().getMovies("15f84396",strings[0]);
        try {

            return call.execute().body();
        } catch (IOException e) {
            System.out.println("MARIOOOOOOOOOOOOOOOOOOOOOO");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(MovieList movieList) {
        if(movieList!=null){
            for(Movie t: movieList.Search) {
                repository.insert(t);
            }
        }

    }

}
