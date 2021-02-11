package me.turkergoksu.kefilm.model.people

import com.google.gson.annotations.SerializedName

/**
 * Created by turkergoksu on 11-Feb-21.
 */
data class Image(
    @SerializedName("profiles")
    val backdrops: List<Backdrop>,
    @SerializedName("id")
    val id: Int
)

data class Backdrop(
    @SerializedName("file_path")
    val filePath: String
)