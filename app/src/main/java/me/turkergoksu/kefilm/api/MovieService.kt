package me.turkergoksu.kefilm.api

import me.turkergoksu.kefilm.upcoming.data.remote.UpcomingResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by turkergoksu on 11-Jun-21.
 */
interface MovieService {

    @GET("/3/movie/upcoming")
    suspend fun getUpcomingMovies(): Response<UpcomingResponse>
}