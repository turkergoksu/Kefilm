package me.turkergoksu.kefilm.search.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.turkergoksu.kefilm.api.MovieService
import me.turkergoksu.kefilm.core.DataSource
import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.di.module.IoDispatcher
import javax.inject.Inject

/**
 * Created by turkergoksu on 16-Nov-21.
 */
class SearchRemoteDataSource @Inject constructor(
    private val movieService: MovieService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : DataSource.Remote {

    suspend fun getSearchResponse(query: String): Resource<SearchResponse> =
        withContext(dispatcher) {
            val response = try {
                movieService.getSearchResponse(query)
            } catch (e: Exception) {
                null
            }

            return@withContext response.asResource()
        }
}