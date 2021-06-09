package me.turkergoksu.kefilm.v1.model.people

import com.google.gson.annotations.SerializedName


/**
 * Created by turkergoksu on 11-Feb-21.
 */

data class PeopleDetails(
    @SerializedName("biography")
    val biography: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    @SerializedName("profile_path")
    val profilePath: String?
)