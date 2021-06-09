package me.turkergoksu.kefilm.v1.ui.peopledetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.turkergoksu.kefilm.v1.api.MovieServiceProvider
import me.turkergoksu.kefilm.v1.model.moviedetails.Backdrop
import me.turkergoksu.kefilm.v1.model.people.Image
import me.turkergoksu.kefilm.v1.model.people.KnownMovie
import me.turkergoksu.kefilm.v1.model.people.KnownMovieResponseModel
import me.turkergoksu.kefilm.v1.model.people.PeopleDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by turkergoksu on 11-Feb-21.
 */
class PeopleDetailsViewModel : ViewModel() {

    private val movieServiceProvider = MovieServiceProvider()

    private val peopleDetailsLiveData = MutableLiveData<PeopleDetails>()
    private val peopleImageListLiveData = MutableLiveData<List<Backdrop>>()
    private val peopleKnownMovieListLiveData = MutableLiveData<List<KnownMovie>>()

    fun getPeopleDetailsLiveData(peopleId: Int): LiveData<PeopleDetails> {
        fetchPeopleDetails(peopleId)
        return peopleDetailsLiveData
    }

    private fun fetchPeopleDetails(peopleId: Int) {
        movieServiceProvider.movieService.getPeopleDetails(peopleId).enqueue(
            object : Callback<PeopleDetails> {
                override fun onResponse(
                    call: Call<PeopleDetails>,
                    response: Response<PeopleDetails>
                ) {
                    peopleDetailsLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<PeopleDetails>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    fun getPeopleMediaListLiveData(peopleId: Int): LiveData<List<Backdrop>> {
        fetchPeopleMediaList(peopleId)
        return peopleImageListLiveData
    }

    private fun fetchPeopleMediaList(peopleId: Int) {
        movieServiceProvider.movieService.getPeopleImages(peopleId).enqueue(object :
            Callback<Image> {
            override fun onResponse(call: Call<Image>, response: Response<Image>) {
                peopleImageListLiveData.postValue(response.body()?.backdrops)
            }

            override fun onFailure(call: Call<Image>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getPeopleKnownMovieListLiveData(peopleId: Int): LiveData<List<KnownMovie>> {
        fetchPeopleKnownMovieList(peopleId)
        return peopleKnownMovieListLiveData
    }

    private fun fetchPeopleKnownMovieList(peopleId: Int) {
        movieServiceProvider.movieService.getPeopleKnownMovies(peopleId).enqueue(object :
            Callback<KnownMovieResponseModel> {
            override fun onResponse(
                call: Call<KnownMovieResponseModel>,
                response: Response<KnownMovieResponseModel>
            ) {
                peopleKnownMovieListLiveData.postValue(response.body()?.knownMovies)
            }

            override fun onFailure(call: Call<KnownMovieResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}