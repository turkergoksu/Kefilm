package me.turkergoksu.kefilm.popular.domain

import me.turkergoksu.kefilm.popular.data.PopularMovieItem
import me.turkergoksu.kefilm.popular.data.PopularMovieResponse
import me.turkergoksu.kefilm.popular.data.PopularResponse
import javax.inject.Inject

/**
 * Created by turkergoksu on 23-Jun-21.
 */
class PopularMovieMapper @Inject constructor() {
    fun mapFromResponse(response: PopularResponse?) = response?.popularMovieResponseList?.map {
        mapFromResponse(it)
    } ?: listOf()

    fun mapFromResponse(response: PopularMovieResponse?) = PopularMovieItem(
        id = response?.id ?: throw NullPointerException("PopularMovieResponse id is null."),
        backdropPath = response.backdropPath ?: "",
        title = response.title ?: "",
        popularity = response.popularity?.toInt() ?: 0
    )
}