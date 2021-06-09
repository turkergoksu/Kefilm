package me.turkergoksu.kefilm.v1.model.moviedetails

import com.google.gson.annotations.SerializedName


/**
 * Created by turkergoksu on 23-Jun-20, 10:44 PM
 */

data class Image(
    @SerializedName("backdrops")
    val backdrops: List<Backdrop>,
    @SerializedName("id")
    val id: Int
)

data class Backdrop(
    @SerializedName("file_path")
    val filePath: String
)