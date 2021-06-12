package me.turkergoksu.kefilm.movie_detail.data

import me.turkergoksu.kefilm.core.Resource
import me.turkergoksu.kefilm.movie_detail.data.remote.MovieDetailRemoteDataSource
import me.turkergoksu.kefilm.movie_detail.data.remote.MovieDetailResponse
import javax.inject.Inject

/**
 * Created by turkergoksu on 12-Jun-21.
 */
class MovieDetailRepository @Inject constructor(
    private val remoteDataSource: MovieDetailRemoteDataSource
) {

    suspend fun fetchRemoteMovieDetail(movieId: Int): Resource<MovieDetailResponse> =
        remoteDataSource.getMovieDetail(movieId)
}