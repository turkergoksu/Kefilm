package me.turkergoksu.kefilm.data.remote.api

import me.turkergoksu.kefilm.model.genre.GenreResponseModel
import me.turkergoksu.kefilm.model.moviedetails.MovieDetails
import me.turkergoksu.kefilm.model.toprated.TopRatedResponseModel
import me.turkergoksu.kefilm.model.upcoming.UpcomingResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by turkergoksu on 30-Mar-20, 6:11 PM
 */

interface MovieService {

    @GET("/3/movie/upcoming")
    fun getUpcomingMovies() : Call<UpcomingResponseModel>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int) : Call<TopRatedResponseModel>

    @GET("/3/genre/movie/list")
    fun getGenres() : Call<GenreResponseModel>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int) : Call<MovieDetails>
}