package me.turkergoksu.kefilm.v1.model.popular

import androidx.paging.PageKeyedDataSource
import me.turkergoksu.kefilm.v1.api.MovieServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by turkergoksu on 25-Jun-20, 8:06 PM
 */

class PopularItemDataSource : PageKeyedDataSource<Int, PopularMovieItem>() {

    private val movieServiceProvider = MovieServiceProvider()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PopularMovieItem>
    ) {
        val data: Call<PopularResponseModel> =
            movieServiceProvider.movieService.getPopularMovies(1)
        data.enqueue(object : Callback<PopularResponseModel> {
            override fun onFailure(call: Call<PopularResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<PopularResponseModel>,
                response: Response<PopularResponseModel>
            ) {
                val initialPopularMoviePagedList = response.body()?.results
                callback.onResult(initialPopularMoviePagedList!!, null, 2)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PopularMovieItem>) {
        val data = movieServiceProvider.movieService.getPopularMovies(params.key)
        data.enqueue(object : Callback<PopularResponseModel> {
            override fun onFailure(call: Call<PopularResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<PopularResponseModel>,
                response: Response<PopularResponseModel>
            ) {
                val popularMoviePagedList = response.body()?.results
                callback.onResult(popularMoviePagedList!!, params.key + 1)
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PopularMovieItem>
    ) {
        TODO("Not yet implemented")
    }
}