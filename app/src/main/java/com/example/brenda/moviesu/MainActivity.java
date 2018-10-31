package com.example.brenda.moviesu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.brenda.moviesu.Adapter.MovieAdapter;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mView;
    MovieAdapter adapter;
    List<Result> results;
    Result result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = (RecyclerView)findViewById(R.id.movieView);
        mView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        result = new GsonBuilder()
                .create()
                .fromJson(getIntent().getStringExtra("movie"), Result.class);

        movieLoad("popular");



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_sort, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.popular){
            movieLoad("popular");
        }
        else if (id == R.id.toprated){
            movieLoad("toprated");
        }

        return super.onOptionsItemSelected(item);
    }

    private void movieLoad(String value){

        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        retrofit2.Call<Movie> call = null;

        if (value.equals("popular")){
            call = api.getPopular();
        }

        else if (value.equals("toprated")){
            call = api.getTopRated();
        }
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(retrofit2.Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();
                adapter = new MovieAdapter(results);
                adapter.setData(movie.getResults());
                mView.setAdapter(adapter);
            }

            @Override
            public void onFailure(retrofit2.Call<Movie> call, Throwable t) {

            }
        });


    }

}
