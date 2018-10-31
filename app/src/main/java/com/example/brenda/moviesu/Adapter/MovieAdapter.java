package com.example.brenda.moviesu.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brenda.moviesu.DetailMovieActivity;
import com.example.brenda.moviesu.R;
import com.example.brenda.moviesu.Result;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    List<Result> results;
//    TextView tvJudul;

    public MovieAdapter(List<Result> results) {
        this.results = results;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_movie, parent, false);

        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, final int position) {
        Picasso.with(holder.itemView.getContext())
                .load("http://image.tmdb.org/t/p/w185/"+results.get(position).getPosterPath())
                .into(holder.Poster);

//        tvJudul = (TextView)holder.itemView.findViewById(R.id.tvJudul);
//        tvJudul.setText(results.get(position).getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result data = results.get(position);
                Intent i = new Intent(holder.itemView.getContext(), DetailMovieActivity.class);
                i.putExtra("movie", new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    public void setData(List<Result> results){
        this.results = results;
    }


    @Override
    public int getItemCount() {
        return results.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder{
        ImageView Poster;
        TextView tvJudul;

        public MovieHolder(View itemView) {
            super(itemView);
            Poster = (ImageView)itemView.findViewById(R.id.poster);
//            tvJudul = (TextView)itemView.findViewById(R.id.tvJudul);
        }
    }


}
