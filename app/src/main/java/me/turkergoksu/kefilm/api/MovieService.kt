package me.turkergoksu.kefilm.api

import me.turkergoksu.kefilm.movie_detail.data.remote.MovieDetailResponse
import me.turkergoksu.kefilm.now_playing.data.remote.NowPlayingResponse
import me.turkergoksu.kefilm.popular.data.PopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by turkergoksu on 11-Jun-21.
 */
interface MovieService {

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(): Response<NowPlayingResponse>

    @GET("/3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): Response<MovieDetailResponse>

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(): Response<PopularResponse>
}