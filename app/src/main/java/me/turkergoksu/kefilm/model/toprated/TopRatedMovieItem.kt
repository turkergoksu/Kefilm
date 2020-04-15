package me.turkergoksu.kefilm.model.toprated

import com.google.gson.annotations.SerializedName

/**
 * Created by turkergoksu on 13-Apr-20, 12:14 AM
 */

data class TopRatedMovieItem(
    @SerializedName("id")
    val id: Long,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Float,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("overview")
    val overview: String
)