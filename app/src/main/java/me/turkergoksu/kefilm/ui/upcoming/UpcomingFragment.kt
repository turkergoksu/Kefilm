package me.turkergoksu.kefilm.ui.upcoming

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSnapHelper
import kotlinx.android.synthetic.main.fragment_upcoming.*

import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.data.remote.api.MovieServiceProvider
import me.turkergoksu.kefilm.model.upcoming.UpcomingResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment() {

    private val adapter = UpcomingMovieAdapter()
    private val movieServiceProvider = MovieServiceProvider()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView_upcoming_movies.adapter = adapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView_upcoming_movies)

        movieServiceProvider.movieService.getUpcomingMovies().enqueue(object :
            Callback<UpcomingResponseModel> {
            override fun onFailure(call: Call<UpcomingResponseModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<UpcomingResponseModel>,
                response: Response<UpcomingResponseModel>
            ) {
                Log.d(TAG, response.body()?.results?.get(0).toString())
                adapter.setUpcomingMovieList(response.body()?.results)
            }

        })
    }

    companion object {
        val TAG = UpcomingFragment::class.java.simpleName
    }

}
