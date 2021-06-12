package me.turkergoksu.kefilm.upcoming.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.PagerSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import me.turkergoksu.kefilm.core.BaseFragment
import me.turkergoksu.kefilm.databinding.FragmentUpcomingBinding

/**
 * Created by turkergoksu on 09-Jun-21.
 */
@AndroidEntryPoint
class UpcomingFragment : BaseFragment<FragmentUpcomingBinding, UpcomingViewModel>() {

    override val viewModel: UpcomingViewModel by viewModels()

    override fun getViewBinding() = FragmentUpcomingBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            binding.viewState = it
        }
    }

    private fun setupRecyclerView() {
        val adapter = UpcomingMovieItemAdapter()
        binding.recyclerViewUpcomingList.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewUpcomingList)
    }

    companion object {
        fun newInstance() = UpcomingFragment()
    }
}