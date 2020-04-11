package me.turkergoksu.kefilm.ui.upcoming

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper

import me.turkergoksu.kefilm.data.remote.api.MovieServiceProvider
import me.turkergoksu.kefilm.databinding.FragmentUpcomingBinding
import me.turkergoksu.kefilm.model.upcoming.UpcomingMovieItem
import me.turkergoksu.kefilm.model.upcoming.UpcomingResponseModel
import me.turkergoksu.kefilm.utils.ImageLoadingUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment() {

    private val adapter = UpcomingMovieAdapter()
    private val movieServiceProvider = MovieServiceProvider()
    private lateinit var scrollListener: CustomScrollListener

    private lateinit var binding: FragmentUpcomingBinding
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpcomingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewUpcomingMovies.adapter = adapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewUpcomingMovies)

        layoutManager = binding.recyclerViewUpcomingMovies.layoutManager as LinearLayoutManager

        scrollListener = CustomScrollListener(
            context!!,
            layoutManager,
            binding
        )
        binding.recyclerViewUpcomingMovies.addOnScrollListener(scrollListener)

        movieServiceProvider.movieService.getUpcomingMovies().enqueue(object :
            Callback<UpcomingResponseModel> {
            override fun onFailure(call: Call<UpcomingResponseModel>, t: Throwable) {
                // TODO
            }

            override fun onResponse(
                call: Call<UpcomingResponseModel>,
                response: Response<UpcomingResponseModel>
            ) {
                val upcomingMovieList: List<UpcomingMovieItem> = response.body()?.results!!

                // Set listener's movie list
                scrollListener.setUpcomingMovieList(upcomingMovieList)

                // Set background image for first item
                ImageLoadingUtil.changeMainFragmentBackground(
                    context!!,
                    binding,
                    upcomingMovieList.first().posterPath
                )

                // Set adapter's movie list
                adapter.setUpcomingMovieList(upcomingMovieList)
            }

        })
    }

    companion object {
        val TAG = UpcomingFragment::class.java.simpleName
    }

}
