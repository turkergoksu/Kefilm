package me.turkergoksu.kefilm.v1.api

import me.turkergoksu.kefilm.v1.model.genre.GenreResponseModel
import me.turkergoksu.kefilm.v1.model.moviedetails.*
import me.turkergoksu.kefilm.v1.model.people.KnownMovieResponseModel
import me.turkergoksu.kefilm.v1.model.people.PeopleDetails
import me.turkergoksu.kefilm.v1.model.popular.PopularResponseModel
import me.turkergoksu.kefilm.v1.model.toprated.TopRatedResponseModel
import me.turkergoksu.kefilm.v1.model.upcoming.UpcomingResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by turkergoksu on 30-Mar-20, 6:11 PM
 */

interface MovieService {

    @GET("/3/movie/upcoming")
    fun getUpcomingMovies(): Call<UpcomingResponseModel>

    @GET("/3/movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int): Call<TopRatedResponseModel>

    @GET("/3/genre/movie/list")
    fun getGenres(): Call<GenreResponseModel>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Call<MovieDetails>

    @GET("/3/movie/{movie_id}/credits")
    fun getMovieCast(@Path("movie_id") movieId: Int): Call<Cast>

    @GET("/3/movie/{movie_id}/images")
    fun getMovieImages(@Path("movie_id") movieId: Int): Call<Image>

    @GET("/3/movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") movieId: Int): Call<SimilarMovieResponseModel>

    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Call<PopularResponseModel>

    @GET("/3/person/{person_id}")
    fun getPeopleDetails(@Path("person_id") personId: Int): Call<PeopleDetails>

    @GET("/3/person/{person_id}/images")
    fun getPeopleImages(@Path("person_id") personId: Int): Call<me.turkergoksu.kefilm.v1.model.people.Image>

    @GET("/3/person/{person_id}/movie_credits")
    fun getPeopleKnownMovies(@Path("person_id") personId: Int): Call<KnownMovieResponseModel>

    @GET("/3/movie/{movie_id}/videos")
    fun getMovieVideos(@Path("movie_id") movieId: Int): Call<VideoResponseModel>
}