package me.turkergoksu.kefilm.ui.upcoming

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper

import me.turkergoksu.kefilm.databinding.FragmentUpcomingBinding
import me.turkergoksu.kefilm.model.upcoming.UpcomingMovieItem
import me.turkergoksu.kefilm.utils.ImageLoadingUtil

/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment() {

    private val adapter = UpcomingMovieAdapter()
    private val upcomingViewModel: UpcomingViewModel by viewModels()
    private lateinit var scrollListener: CustomScrollListener

    private lateinit var binding: FragmentUpcomingBinding
    private lateinit var layoutManager: LinearLayoutManager

    private var upcomingMovieList = listOf<UpcomingMovieItem>()

    companion object {
        val TAG = UpcomingFragment::class.java.simpleName
    }

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

        upcomingViewModel.getUpcomingMovieListLiveData()
            .observe(viewLifecycleOwner, Observer { upcomingMovieList ->
                this.upcomingMovieList = upcomingMovieList

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

                // Hide progress bar
                binding.progressBarLoading.hide()
            })
    }

    override fun onResume() {
        super.onResume()
        // Set background image
        if (upcomingMovieList.isNotEmpty()) {
            ImageLoadingUtil.changeMainFragmentBackground(
                context!!,
                binding,
                upcomingMovieList[scrollListener.currentMovieItemIndex].posterPath
            )
        }
    }

}
