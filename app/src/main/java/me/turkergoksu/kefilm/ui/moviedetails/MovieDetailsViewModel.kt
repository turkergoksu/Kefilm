package me.turkergoksu.kefilm.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.turkergoksu.kefilm.data.remote.api.MovieServiceProvider
import me.turkergoksu.kefilm.model.moviedetails.MovieDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by turkergoksu on 15-Jun-20, 8:04 PM
 */

class MovieDetailsViewModel : ViewModel() {

    private val movieServiceProvider = MovieServiceProvider()

    private val movieDetailsLiveData = MutableLiveData<MovieDetails>()

    fun getMovieDetailsLiveData(movieId: Int): LiveData<MovieDetails> {
        fetchMovieDetails(movieId)
        return movieDetailsLiveData
    }

    private fun fetchMovieDetails(movieId: Int) {
        movieServiceProvider.movieService.getMovieDetails(movieId).enqueue(object :
            Callback<MovieDetails> {
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                movieDetailsLiveData.postValue(response.body())
            }
        })
    }
}