package me.turkergoksu.kefilm.v1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.turkergoksu.kefilm.v1.api.MovieServiceProvider
import me.turkergoksu.kefilm.v1.model.genre.Genre
import me.turkergoksu.kefilm.v1.model.genre.GenreResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by turkergoksu on 13-Apr-20, 6:43 AM
 */

class GenreViewModel : ViewModel() {
    private val movieServiceProvider = MovieServiceProvider()

    private val genreHashMapLiveData = MutableLiveData<HashMap<Int, Genre>>()

    fun getGenreHashMapLiveData(): LiveData<HashMap<Int, Genre>> {
        fetchGenres()
        return genreHashMapLiveData
    }

    private fun fetchGenres() {
        movieServiceProvider.movieService.getGenres().enqueue(object : Callback<GenreResponseModel> {
            override fun onFailure(call: Call<GenreResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<GenreResponseModel>,
                response: Response<GenreResponseModel>
            ) {
                val genreList = response.body()?.genres
                val genreHashMap = hashMapOf<Int, Genre>()
                for (genre in genreList!!){
                    genreHashMap[genre.id] = genre
                }
                genreHashMapLiveData.postValue(genreHashMap)
            }
        })
    }

}