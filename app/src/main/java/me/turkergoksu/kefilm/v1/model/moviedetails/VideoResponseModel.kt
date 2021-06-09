package me.turkergoksu.kefilm.v1.model.moviedetails

import com.google.gson.annotations.SerializedName

/**
 * Created by turkergoksu on 13-Feb-21.
 */
data class VideoResponseModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val results: List<Video>?
)

data class Video(
    @SerializedName("id")
    val id: String?,
    @SerializedName("iso_3166_1")
    val iso31661: String?,
    @SerializedName("iso_639_1")
    val iso6391: String?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("site")
    val site: String?,
    @SerializedName("size")
    val size: Int?,
    @SerializedName("type")
    val type: String?
)