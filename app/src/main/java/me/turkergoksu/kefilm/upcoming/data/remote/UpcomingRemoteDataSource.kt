package me.turkergoksu.kefilm.upcoming.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.turkergoksu.kefilm.api.MovieService
import me.turkergoksu.kefilm.core.DataSource
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.di.module.IoDispatcher
import javax.inject.Inject

/**
 * Created by turkergoksu on 11-Jun-21.
 */
class UpcomingRemoteDataSource @Inject constructor(
    private val movieService: MovieService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : DataSource.Remote {

    suspend fun getUpcomingMovies(): Resource<UpcomingResponse> =
        withContext(dispatcher) {
            val response = try {
                movieService.getUpcomingMovies()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

            return@withContext response.asResource()
        }
}