package me.turkergoksu.kefilm.movie_detail.ui

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
}