package me.turkergoksu.kefilm.now_playing.data

import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.now_playing.data.remote.NowPlayingRemoteDataSource
import me.turkergoksu.kefilm.now_playing.data.remote.NowPlayingResponse
import javax.inject.Inject

/**
 * Created by turkergoksu on 11-Jun-21.
 */
class NowPlayingRepository @Inject constructor(
    private val remoteDataSource: NowPlayingRemoteDataSource
) {

    suspend fun fetchRemoteNowPlayingMovies(): Resource<NowPlayingResponse> {
        return remoteDataSource.getNowPlayingMovies()
    }
}