package me.turkergoksu.kefilm.v1.model.people

import com.google.gson.annotations.SerializedName
import me.turkergoksu.kefilm.v1.model.moviedetails.Backdrop

/**
 * Created by turkergoksu on 11-Feb-21.
 */
data class Image(
    @SerializedName("profiles")
    val backdrops: List<Backdrop>,
    @SerializedName("id")
    val id: Int
)