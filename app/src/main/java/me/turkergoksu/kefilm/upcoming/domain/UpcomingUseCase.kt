package me.turkergoksu.kefilm.upcoming.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.upcoming.data.UpcomingMovieItem
import me.turkergoksu.kefilm.upcoming.data.UpcomingRepository
import javax.inject.Inject

/**
 * Created by turkergoksu on 11-Jun-21.
 */
class UpcomingUseCase @Inject constructor(
    private val repository: UpcomingRepository,
    private val upcomingMovieMapper: UpcomingMovieMapper,
) {

    fun fetchUpcomingMovies(): Flow<Resource<List<UpcomingMovieItem>>> = flow {
        emit(Resource.Loading())

        emit(
            repository.fetchRemoteUpcomingMovies()
                .mapData { upcomingMovieMapper.mapFromResponse(it.upcomingMovieResponseList) })
    }
}