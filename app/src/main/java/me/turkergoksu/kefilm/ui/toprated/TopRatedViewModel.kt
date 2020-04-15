package me.turkergoksu.kefilm.ui.toprated

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.turkergoksu.kefilm.data.remote.api.MovieServiceProvider
import me.turkergoksu.kefilm.model.toprated.TopRatedMovieItem
import me.turkergoksu.kefilm.model.toprated.TopRatedResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by turkergoksu on 13-Apr-20, 1:23 AM
 */

class TopRatedViewModel : ViewModel() {
    private val movieServiceProvider = MovieServiceProvider()

    private val topRatedMovieListLiveData = MutableLiveData<List<TopRatedMovieItem>>()

    fun getTopRatedMovieListLiveData(): LiveData<List<TopRatedMovieItem>> {
        fetchTopRatedMovies()
        return topRatedMovieListLiveData
    }

    private fun fetchTopRatedMovies() {
        movieServiceProvider.movieService.getTopRatedMovies().enqueue(object :
            Callback<TopRatedResponseModel> {
            override fun onFailure(call: Call<TopRatedResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<TopRatedResponseModel>,
                response: Response<TopRatedResponseModel>
            ) {
                topRatedMovieListLiveData.postValue(response.body()?.results)
            }
        })
    }
}