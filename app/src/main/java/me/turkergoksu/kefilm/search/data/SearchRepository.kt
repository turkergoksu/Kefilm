package me.turkergoksu.kefilm.search.data

import me.turkergoksu.kefilm.core.Resource
import javax.inject.Inject

/**
 * Created by turkergoksu on 16-Nov-21.
 */
class SearchRepository @Inject constructor(private val remoteDataSource: SearchRemoteDataSource) {

    suspend fun fetchSearchResults(query: String): Resource<SearchResponse> =
        remoteDataSource.getSearchResponse(query)
}