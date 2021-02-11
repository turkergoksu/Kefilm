package me.turkergoksu.kefilm.model.people

import com.google.gson.annotations.SerializedName

/**
 * Created by turkergoksu on 11-Feb-21.
 */
data class KnownMovieResponseModel(
    @SerializedName("cast")
    val knownMovies: List<KnownMovie>?
)

data class KnownMovie(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("character")
    val characterName: String?
)