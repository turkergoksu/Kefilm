package me.turkergoksu.kefilm.model.genre

import com.google.gson.annotations.SerializedName

/**
 * Created by turkergoksu on 13-Apr-20, 12:21 AM
 */

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)