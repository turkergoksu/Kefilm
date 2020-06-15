package me.turkergoksu.kefilm.model.moviedetails
import com.google.gson.annotations.SerializedName
import me.turkergoksu.kefilm.model.genre.Genre


/**
 * Created by turkergoksu on 15-Jun-20, 7:44 PM
 */

data class MovieDetails(
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
)