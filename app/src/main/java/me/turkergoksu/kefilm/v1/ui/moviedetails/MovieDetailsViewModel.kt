package me.turkergoksu.kefilm.v1.ui.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.turkergoksu.kefilm.v1.api.MovieServiceProvider
import me.turkergoksu.kefilm.v1.model.moviedetails.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by turkergoksu on 15-Jun-20, 8:04 PM
 */

class MovieDetailsViewModel : ViewModel() {

    private val movieServiceProvider = MovieServiceProvider()

    private val movieDetailsLiveData = MutableLiveData<MovieDetails>()
    private val movieCastLiveData = MutableLiveData<List<CastItem>>()
    private val movieBackdropListLiveData = MutableLiveData<List<Backdrop>>()
    private val movieSimilarMovieListLiveData = MutableLiveData<List<SimilarMovie>>()
    private val movieVideoListLiveData = MutableLiveData<List<Video>>()

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

    fun getMovieCastLiveData(movieId: Int): LiveData<List<CastItem>> {
        fetchMovieCast(movieId)
        return movieCastLiveData
    }

    private fun fetchMovieCast(movieId: Int) {
        movieServiceProvider.movieService.getMovieCast(movieId).enqueue(object :
            Callback<Cast> {
            override fun onFailure(call: Call<Cast>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<Cast>, response: Response<Cast>) {
                movieCastLiveData.postValue(response.body()!!.cast)
            }
        })
    }

    fun getMovieBackdropListLiveData(movieId: Int): LiveData<List<Backdrop>> {
        fetchMovieBackdropList(movieId)
        return movieBackdropListLiveData
    }

    private fun fetchMovieBackdropList(movieId: Int) {
        movieServiceProvider.movieService.getMovieImages(movieId).enqueue(object :
            Callback<Image> {
            override fun onFailure(call: Call<Image>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<Image>, response: Response<Image>) {
                movieBackdropListLiveData.postValue(response.body()!!.backdrops)
            }
        })
    }

    fun getSimilarMovieListLiveData(movieId: Int): LiveData<List<SimilarMovie>> {
        fetchSimilarMovieList(movieId)
        return movieSimilarMovieListLiveData
    }

    private fun fetchSimilarMovieList(movieId: Int) {
        movieServiceProvider.movieService.getSimilarMovies(movieId).enqueue(object :
            Callback<SimilarMovieResponseModel> {
            override fun onFailure(call: Call<SimilarMovieResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<SimilarMovieResponseModel>,
                response: Response<SimilarMovieResponseModel>
            ) {
                movieSimilarMovieListLiveData.postValue(response.body()!!.results)
            }
        })
    }

    fun getMovieVideoList(movieId: Int): LiveData<List<Video>> {
        fetchMovieVideoList(movieId)
        return movieVideoListLiveData
    }

    private fun fetchMovieVideoList(movieId: Int) {
        movieServiceProvider.movieService.getMovieVideos(movieId).enqueue(object :
            Callback<VideoResponseModel> {
            override fun onResponse(
                call: Call<VideoResponseModel>,
                response: Response<VideoResponseModel>
            ) {
                movieVideoListLiveData.postValue(response.body()?.results)
            }

            override fun onFailure(call: Call<VideoResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        }

        )
    }
}