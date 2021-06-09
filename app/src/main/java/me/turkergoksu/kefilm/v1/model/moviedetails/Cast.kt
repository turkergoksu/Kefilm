package me.turkergoksu.kefilm.v1.model.moviedetails

import com.google.gson.annotations.SerializedName


/**
 * Created by turkergoksu on 23-Jun-20, 9:12 PM
 */

data class Cast(
    @SerializedName("cast")
    val cast: List<CastItem>,
    @SerializedName("id")
    val id: Int
)

data class CastItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String
)