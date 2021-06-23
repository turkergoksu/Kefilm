package me.turkergoksu.kefilm.popular.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.popular.data.PopularMovieItem
import me.turkergoksu.kefilm.popular.data.PopularRepository
import javax.inject.Inject

/**
 * Created by turkergoksu on 23-Jun-21.
 */
class PopularUseCase @Inject constructor(
    private val repository: PopularRepository,
    private val mapper: PopularMovieMapper
) {
    fun fetchPopularMovies(): Flow<Resource<List<PopularMovieItem>>> = flow {
        emit(Resource.Loading())

        emit(repository.fetchRemotePopularMovies().mapData { mapper.mapFromResponse(it) })
    }
}