package me.turkergoksu.kefilm.now_playing.data.remote
import com.google.gson.annotations.SerializedName


/**
 * Created by turkergoksu on 17-Jun-21.
 */
data class NowPlayingResponse(
    @SerializedName("results")
    val movieResponseList: List<NowPlayingMovieResponse>?,
)

data class NowPlayingMovieResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean?,
)