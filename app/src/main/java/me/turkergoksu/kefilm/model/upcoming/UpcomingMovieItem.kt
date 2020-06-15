package me.turkergoksu.kefilm.model.upcoming

import com.google.gson.annotations.SerializedName

/**
 * Created by turkergoksu on 29-Mar-20, 5:13 PM
 */

data class UpcomingMovieItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("overview")
    val overview: String
)