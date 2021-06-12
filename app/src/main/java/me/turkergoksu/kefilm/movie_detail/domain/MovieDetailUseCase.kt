package me.turkergoksu.kefilm.movie_detail.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.movie_detail.data.MovieDetail
import me.turkergoksu.kefilm.movie_detail.data.MovieDetailRepository
import javax.inject.Inject

/**
 * Created by turkergoksu on 12-Jun-21.
 */
class MovieDetailUseCase @Inject constructor(
    private val repository: MovieDetailRepository,
    private val movieDetailMapper: MovieDetailMapper
) {

    fun fetchMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())

        emit(
            repository.fetchRemoteMovieDetail(movieId)
                .mapData { movieDetailMapper.mapFromResponse(it) })
    }
}