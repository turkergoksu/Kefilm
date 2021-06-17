package me.turkergoksu.kefilm.movie_detail.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import me.turkergoksu.kefilm.core.BaseFragment
import me.turkergoksu.kefilm.databinding.FragmentMovieDetailBinding

/**
 * Created by turkergoksu on 12-Jun-21.
 */
@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding, MovieDetailViewModel>() {

    override val viewModel: MovieDetailViewModel by viewModels()

    override fun getViewBinding() = FragmentMovieDetailBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // FIXME: 17-Jun-21
        viewModel.fetchMovieDetail(0)
        viewModel.movieDetail.observe(viewLifecycleOwner) {
            binding.viewState = it
        }
    }
}