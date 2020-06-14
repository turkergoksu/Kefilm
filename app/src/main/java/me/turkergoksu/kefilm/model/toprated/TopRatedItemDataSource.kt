package me.turkergoksu.kefilm.model.toprated

import android.util.Log
import androidx.paging.PageKeyedDataSource
import me.turkergoksu.kefilm.data.remote.api.MovieServiceProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by turkergoksu on 14-Jun-20, 2:42 AM
 */

class TopRatedItemDataSource : PageKeyedDataSource<Int, TopRatedMovieItem>() {

    private val movieServiceProvider = MovieServiceProvider()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TopRatedMovieItem>
    ) {
        val data: Call<TopRatedResponseModel> =
            movieServiceProvider.movieService.getTopRatedMovies(1)
        data.enqueue(object : Callback<TopRatedResponseModel> {
            override fun onFailure(call: Call<TopRatedResponseModel>, t: Throwable) {
                Log.d("ses", "" + t.localizedMessage)
            }

            override fun onResponse(
                call: Call<TopRatedResponseModel>,
                response: Response<TopRatedResponseModel>
            ) {
                val initialTopRatedMoviePagedList = response.body()?.results
                callback.onResult(initialTopRatedMoviePagedList!!, null, 2)
            }
        })

    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, TopRatedMovieItem>
    ) {
        val data = movieServiceProvider.movieService.getTopRatedMovies(params.key)
        data.enqueue(object : Callback<TopRatedResponseModel> {
            override fun onFailure(call: Call<TopRatedResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<TopRatedResponseModel>,
                response: Response<TopRatedResponseModel>
            ) {
                val topRatedMoviePagedList = response.body()?.results
                callback.onResult(topRatedMoviePagedList!!, params.key + 1)
            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, TopRatedMovieItem>
    ) {
        TODO("Not yet implemented")
    }


}