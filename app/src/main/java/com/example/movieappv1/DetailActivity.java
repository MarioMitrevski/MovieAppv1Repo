package com.example.movieappv1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
//import com.example.movieappv1.asynctask.DetailAsyncTask;
import com.example.movieappv1.client.OMDBApiClient;
import com.example.movieappv1.db.Repository;
import com.example.movieappv1.models.Movie;
import com.example.movieappv1.models.MovieDetail;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {

   // DetailAsyncTask detailAsyncTask;
    Repository repository;
    TextView title,year;
    EditText plot;
    String naslov,godina,tekst,slika;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        //Bundle bundle = intent.getExtras();

       // repository = (Repository) bundle.getSerializable("repo");
        //DetailasyncTask = new DetailAsyncTask(repository);
        //System.out.println(bundle.get("imdbId").toString());
        //DetailasyncTask.execute(bundle.get("imdbId").toString());


        initViews();
        String id = intent.getStringExtra("imdbId");
        System.out.println(id);

        Call<MovieDetail> call = OMDBApiClient.getService().getMovie("15f84396",id);
        call.enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {

                slika = response.body().getPoster();

                tekst = response.body().getPlot();
                naslov = response.body().getTitle();
                godina = response.body().getYear();

                System.out.println(tekst);
                setData();


            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                System.out.println("varga");

            }
        });


        //detailAsyncTask = new DetailAsyncTask(repository);
        //detailAsyncTask.execute(id);

       // System.out.println(DetailasyncTask.isCancelled());
        //initData();


    }

    void initViews(){
        title = findViewById(R.id.textView3);
        year = findViewById(R.id.textView4);
        plot = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);


    }
    void initData() {
/*
        repository.getMovie().observe(this, new Observer<MovieDetail>() {
            @Override
            public void onChanged(MovieDetail movieDetail) {

                setData(movieDetail);
            }
        });*/
    }

    void setData(){
        System.out.println("SETIRA DATA VO DETAILS");
        System.out.println(" NASLOV ::::: " + naslov);
        title.setText(naslov);
        plot.setText(tekst);
        year.setText(godina);




        Picasso.with(getApplicationContext())
                .load(slika)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(imageView);

    }


}
