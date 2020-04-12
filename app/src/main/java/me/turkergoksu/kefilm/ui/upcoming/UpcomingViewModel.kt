package me.turkergoksu.kefilm.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.turkergoksu.kefilm.data.remote.api.MovieServiceProvider
import me.turkergoksu.kefilm.model.upcoming.UpcomingMovieItem
import me.turkergoksu.kefilm.model.upcoming.UpcomingResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by turkergoksu on 12-Apr-20, 5:41 AM
 */

class UpcomingViewModel : ViewModel() {
    private val movieServiceProvider = MovieServiceProvider()

    private val upcomingMovieListLiveData = MutableLiveData<List<UpcomingMovieItem>>()

    fun getUpcomingMovieListLiveData(): LiveData<List<UpcomingMovieItem>> {
        fetchUpcomingMovies()
        return upcomingMovieListLiveData
    }

    private fun fetchUpcomingMovies() {
        movieServiceProvider.movieService.getUpcomingMovies().enqueue(object :
            Callback<UpcomingResponseModel> {
            override fun onFailure(call: Call<UpcomingResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<UpcomingResponseModel>,
                response: Response<UpcomingResponseModel>
            ) {
                upcomingMovieListLiveData.postValue(response.body()?.results)
            }
        })
    }
}