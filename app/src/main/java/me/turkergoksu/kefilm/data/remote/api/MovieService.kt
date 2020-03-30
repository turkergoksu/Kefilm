package me.turkergoksu.kefilm.data.remote.api

import me.turkergoksu.kefilm.model.upcoming.UpcomingResponseModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by turkergoksu on 30-Mar-20, 6:11 PM
 */

interface MovieService {

    @GET("/3/movie/upcoming")
    fun getUpcomingMovies() : Call<UpcomingResponseModel>
}