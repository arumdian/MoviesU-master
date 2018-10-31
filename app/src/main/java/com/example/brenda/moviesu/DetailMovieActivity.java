package com.example.brenda.moviesu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;



public class DetailMovieActivity extends AppCompatActivity {

    ImageView poster;
    TextView tvJudul, tvTanggal, tvRating, tvSinopsis;

    Result result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        poster = (ImageView)findViewById(R.id.poster);
        tvJudul = (TextView)findViewById(R.id.judul);
        tvTanggal = (TextView)findViewById(R.id.tanggal);
        tvRating = (TextView)findViewById(R.id.rating);
        tvSinopsis = (TextView)findViewById(R.id.sinopsis);

        result = new GsonBuilder()
                .create()
                .fromJson(getIntent().getStringExtra("movie"), Result.class);

        Picasso.with(DetailMovieActivity.this)
                .load("http://image.tmdb.org/t/p/original/"+result.getPosterPath())
                .into(poster);

        tvJudul.setText(result.getTitle());
        tvTanggal.setText("Release Date: " + result.getReleaseDate());
        tvRating.setText("Vote Average: " + Double.toString(result.getVoteAverage()));
        tvSinopsis.setText(result.getOverview());

    }
}
