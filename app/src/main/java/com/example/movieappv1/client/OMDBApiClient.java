package com.example.movieappv1.client;


import com.example.movieappv1.models.MovieDetail;
//import com.example.movieappv1.services.MovieDetailsService;
import com.example.movieappv1.services.MovieListService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OMDBApiClient {

    private static Retrofit retrofit = null;

    private static Retrofit getRetrofit() {
        if(retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public static MovieListService getService() {
        return getRetrofit().create(MovieListService.class);
    }


}
