package com.example.brenda.moviesu;

import retrofit2.Call;
import retrofit2.http.GET;



public interface ApiInterface {

    public static String DB_API = "340fc368dacfb581af7039e79cfca64f";

    @GET("popular?api_key="+DB_API)
    Call<Movie> getPopular();

    @GET("top_rated?api_key="+DB_API)
    Call<Movie> getTopRated();



}
