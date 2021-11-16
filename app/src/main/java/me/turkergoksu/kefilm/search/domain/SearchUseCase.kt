package me.turkergoksu.kefilm.search.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.search.data.SearchItem
import me.turkergoksu.kefilm.search.data.SearchRepository
import javax.inject.Inject

/**
 * Created by turkergoksu on 16-Nov-21.
 */
class SearchUseCase @Inject constructor(
    private val repository: SearchRepository,
    private val mapper: SearchMapper,
) {

    fun fetchSearchResults(query: String): Flow<Resource<List<SearchItem>>> = flow {
        emit(Resource.Loading())

        emit(repository.fetchSearchResults(query).mapData { mapper.mapFromResponse(it) })
    }
}