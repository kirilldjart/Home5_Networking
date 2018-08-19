package com.drob.kirill.home5_networking.network;


import com.drob.kirill.home5_networking.data.repository.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbService {
    // http://www.omdbapi.com/?i=tt3896198&apikey=2aaf22ab

    @GET("/")
    Call<MovieResponse> getMovies(@Query("s") String tittle);

    //Alternative way for testing..
    @GET("/")
    Call<MovieResponse> getMoviesWithApi(@Query("s") String tittle, @Query("apikey") String api);

}
