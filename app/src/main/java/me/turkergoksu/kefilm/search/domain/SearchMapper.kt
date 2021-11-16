package me.turkergoksu.kefilm.search.domain

import me.turkergoksu.kefilm.ext.formatDefaultDate
import me.turkergoksu.kefilm.ext.orZero
import me.turkergoksu.kefilm.search.data.SearchItem
import me.turkergoksu.kefilm.search.data.SearchItemResponse
import me.turkergoksu.kefilm.search.data.SearchResponse
import javax.inject.Inject

/**
 * Created by turkergoksu on 16-Nov-21.
 */
class SearchMapper @Inject constructor() {

    fun mapFromResponse(response: SearchResponse): List<SearchItem> =
        response.searchItemResponses?.map { mapFromResponse(it) } ?: listOf()

    private fun mapFromResponse(response: SearchItemResponse): SearchItem =
        SearchItem(
            id = response.id.orZero(),
            title = response.title.orEmpty(),
            posterPath = response.posterPath.orEmpty(),
            releaseYear = response.releaseDate?.formatDefaultDate("yyyy")?.toInt().orZero(),
            voteAverage = response.voteAverage.orZero(),
        )
}