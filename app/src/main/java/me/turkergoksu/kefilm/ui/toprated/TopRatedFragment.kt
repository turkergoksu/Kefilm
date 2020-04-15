package me.turkergoksu.kefilm.ui.toprated

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import me.turkergoksu.kefilm.databinding.FragmentTopRatedBinding
import me.turkergoksu.kefilm.ui.GenreViewModel
import me.turkergoksu.kefilm.utils.ImageLoadingUtil

/**
 * A simple [Fragment] subclass.
 */
class TopRatedFragment : Fragment() {

    private val adapter = TopRatedMovieAdapter()
    private val topRatedViewModel: TopRatedViewModel by viewModels()
    private val genreViewModel: GenreViewModel by viewModels()

    private lateinit var binding: FragmentTopRatedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopRatedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewTopRatedMovies.adapter = adapter

        topRatedViewModel.getTopRatedMovieListLiveData()
            .observe(viewLifecycleOwner, Observer { topRatedMovieList ->
                // Set adapter's movie list
                adapter.setTopRatedMovieList(topRatedMovieList)

                // Hide progress bar
                binding.progressBarLoading.hide()
            })

        genreViewModel.getGenreHashMapLiveData().observe(viewLifecycleOwner, Observer { genres ->
            adapter.setGenreMap(genres)
        })
    }

    override fun onResume() {
        super.onResume()
        // Reset background to default color
        ImageLoadingUtil.resetMainFragmentBackground(context!!, parentFragment!!.view)
    }

}
