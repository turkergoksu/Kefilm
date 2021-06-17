package me.turkergoksu.kefilm.now_playing.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import me.turkergoksu.kefilm.core.BaseFragment
import me.turkergoksu.kefilm.databinding.FragmentNowPlayingBinding

/**
 * Created by turkergoksu on 09-Jun-21.
 */
@AndroidEntryPoint
class NowPlayingFragment : BaseFragment<FragmentNowPlayingBinding, NowPlayingViewModel>() {

    override val viewModel: NowPlayingViewModel by viewModels()

    override fun getViewBinding() = FragmentNowPlayingBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner) {
            binding.viewState = it
        }
    }

    private fun setupRecyclerView() {
        val adapter = NowPlayingMovieItemAdapter()
        binding.recyclerViewNowPlayingList.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewNowPlayingList)
    }

    companion object {
        fun newInstance() = NowPlayingFragment()
    }
}