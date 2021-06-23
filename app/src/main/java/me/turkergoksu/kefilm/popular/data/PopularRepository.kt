package me.turkergoksu.kefilm.popular.data

import me.turkergoksu.kefilm.core.Resource
import javax.inject.Inject

/**
 * Created by turkergoksu on 23-Jun-21.
 */
class PopularRepository @Inject constructor(private val remoteDataSource: PopularResponseRemoteDataSource) {
    suspend fun fetchRemotePopularMovies(): Resource<PopularResponse> =
        remoteDataSource.getPopularResponse()
}