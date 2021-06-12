package me.turkergoksu.kefilm.movie_detail.data.remote

import com.google.gson.annotations.SerializedName


/**
 * Created by turkergoksu on 12-Jun-21.
 */
data class MovieDetailResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)