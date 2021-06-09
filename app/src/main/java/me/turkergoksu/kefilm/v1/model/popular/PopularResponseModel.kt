package me.turkergoksu.kefilm.v1.model.popular

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName


/**
 * Created by turkergoksu on 25-Jun-20, 8:02 PM
 */

data class PopularResponseModel(
    @SerializedName("results")
    val results: List<PopularMovieItem>
)

data class PopularMovieItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String
) {
    companion object {
        val CALLBACK: DiffUtil.ItemCallback<PopularMovieItem> =
            object : DiffUtil.ItemCallback<PopularMovieItem>() {
                override fun areItemsTheSame(
                    oldItem: PopularMovieItem,
                    newItem: PopularMovieItem
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: PopularMovieItem,
                    newItem: PopularMovieItem
                ): Boolean {
                    return true
                }
            }
    }
}