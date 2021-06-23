package me.turkergoksu.kefilm.popular.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.turkergoksu.kefilm.api.MovieService
import me.turkergoksu.kefilm.core.DataSource
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.di.module.IoDispatcher
import javax.inject.Inject

/**
 * Created by turkergoksu on 23-Jun-21.
 */
class PopularResponseRemoteDataSource @Inject constructor(
    private val movieService: MovieService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : DataSource.Remote {

    suspend fun getPopularResponse(): Resource<PopularResponse> =
        withContext(dispatcher) {
            val response = try {
                movieService.getPopularMovies()
            } catch (e: Exception) {
                null
            }

            return@withContext response.asResource()
        }
}