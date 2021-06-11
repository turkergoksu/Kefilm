package me.turkergoksu.kefilm.upcoming.data

import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.upcoming.data.remote.UpcomingRemoteDataSource
import me.turkergoksu.kefilm.upcoming.data.remote.UpcomingResponse
import javax.inject.Inject

/**
 * Created by turkergoksu on 11-Jun-21.
 */
class UpcomingRepository @Inject constructor(
    private val remoteDataSource: UpcomingRemoteDataSource
) {

    suspend fun fetchRemoteUpcomingMovies(): Resource<UpcomingResponse> {
        return remoteDataSource.getUpcomingMovies()
    }
}