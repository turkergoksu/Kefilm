package me.turkergoksu.kefilm.upcoming.data.remote

import com.google.gson.annotations.SerializedName

/**
 * Created by turkergoksu on 11-Jun-21.
 */
data class UpcomingResponse(
    @SerializedName("results")
    val upcomingMovieResponseList: List<UpcomingMovieResponse>?,
)

data class UpcomingMovieResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
)