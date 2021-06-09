package me.turkergoksu.kefilm.v1.model.toprated

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

/**
 * Created by turkergoksu on 13-Apr-20, 12:14 AM
 */

data class TopRatedMovieItem(
    @SerializedName("id")
    val id: Int,
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
) {
    companion object {
        val CALLBACK: DiffUtil.ItemCallback<TopRatedMovieItem> =
            object : DiffUtil.ItemCallback<TopRatedMovieItem>() {
                override fun areItemsTheSame(
                    oldItem: TopRatedMovieItem,
                    newItem: TopRatedMovieItem
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: TopRatedMovieItem,
                    newItem: TopRatedMovieItem
                ): Boolean {
                    return true
                }

            }
    }
}