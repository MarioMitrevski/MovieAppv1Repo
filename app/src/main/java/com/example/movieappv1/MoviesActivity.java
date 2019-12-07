package com.example.movieappv1;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieappv1.adapters.CustomListAdapter;
//import com.example.movieappv1.asynctask.DetailAsyncTask;
import com.example.movieappv1.asynctask.MovieListSyncTask;
import com.example.movieappv1.db.Repository;
import com.example.movieappv1.holders.CustomListViewHolder;
import com.example.movieappv1.models.Movie;


import java.util.List;
import java.util.logging.Logger;

public class MoviesActivity extends AppCompatActivity {

    //List<Movie> dataset = new ArrayList<>();
    CustomListAdapter adapter;
    Logger logger = Logger.getLogger("MoviesActivity");
    MovieListSyncTask asyncTask;
   // DetailAsyncTask detailAsyncTask;
   Repository repository = null;
    Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        repository = new Repository(context);
        //detailAsyncTask = new DetailAsyncTask();

        initToolbar();
        //initListView();
        //DbProvider provider = new DbProvider(getApplicationContext());

        //List<Movie> allTracks = provider.getAllTracks();



        // execution();


    }

    public void initToolbar(){
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void initListView(){

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CustomListAdapter(getItemViewOnClickListener() );
        recyclerView.setAdapter(adapter);

    }
  /*  public void insertElementIntoList(String element) {
       // dataset.add(element);
        adapter.notifyDataSetChanged();
    }*/



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(getOnQueryTextListener());
        return true;

    }
    private SearchView.OnQueryTextListener getOnQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                initListView();
                asyncTask = new MovieListSyncTask(repository);
                asyncTask.execute(query);

                initData();


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                logger.info("Query text change: " + newText);
                return false;
            }
        };
    }

    private void initData() {

        repository.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> data) {
                adapter.updateDataset(data);
            }
        });
    }


/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                ///
                logger.info("Clicked menu item: 1");
                break;

        }
        return super.onOptionsItemSelected(item);

    }*/

    private View.OnClickListener getItemViewOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomListViewHolder holder = (CustomListViewHolder) v.getTag();

                String selectedMovieId = adapter.getClickedItemId(holder);


                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("imdbId",selectedMovieId);

                /*Bundle bundle = new Bundle();
                bundle.putSerializable("repo",repository);*/

                //intent.putExtras(bundle);
                startActivity(intent);
               // logger.info("Clicked: " + dataset.get(adapterPosition));
            }
        };
    }

   /* @Override
    public void loadedMovielist(MovieList movieList) {

        dataset.addAll(movieList.Search);
        adapter.notifyDataSetChanged();

    }*/


}
