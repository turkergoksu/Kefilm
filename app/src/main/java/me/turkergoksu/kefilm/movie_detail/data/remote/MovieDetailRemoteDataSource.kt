package me.turkergoksu.kefilm.movie_detail.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.turkergoksu.kefilm.api.MovieService
import me.turkergoksu.kefilm.core.DataSource
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.di.module.IoDispatcher
import javax.inject.Inject

/**
 * Created by turkergoksu on 12-Jun-21.
 */
class MovieDetailRemoteDataSource @Inject constructor(
    private val movieService: MovieService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : DataSource.Remote {

    suspend fun getMovieDetail(movieId: Int): Resource<MovieDetailResponse> =
        withContext(dispatcher) {
            val response = try {
                movieService.getMovieDetail(movieId)
            } catch (e: Exception) {
                null
            }

            return@withContext response.asResource()
        }
}