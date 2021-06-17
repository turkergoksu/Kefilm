package me.turkergoksu.kefilm.now_playing.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.now_playing.data.NowPlayingMovieItem
import me.turkergoksu.kefilm.now_playing.data.NowPlayingRepository
import javax.inject.Inject

/**
 * Created by turkergoksu on 11-Jun-21.
 */
class NowPlayingUseCase @Inject constructor(
    private val repository: NowPlayingRepository,
    private val mapper: NowPlayingMovieMapper,
) {

    fun fetchNowPlayingMovies(): Flow<Resource<List<NowPlayingMovieItem>>> = flow {
        emit(Resource.Loading())

        emit(
            repository.fetchRemoteNowPlayingMovies()
                .mapData { mapper.mapFromResponse(it.movieResponseList) })
    }
}