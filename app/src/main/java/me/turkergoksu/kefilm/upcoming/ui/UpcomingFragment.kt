package me.turkergoksu.kefilm.upcoming.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import me.turkergoksu.kefilm.core.BaseFragment
import me.turkergoksu.kefilm.databinding.FragmentUpcomingBinding
import me.turkergoksu.kefilm.upcoming.data.UpcomingMovieItem

/**
 * Created by turkergoksu on 09-Jun-21.
 */
class UpcomingFragment : BaseFragment<FragmentUpcomingBinding, UpcomingViewModel>() {

    override val viewModel: UpcomingViewModel by viewModels()

    override fun getViewBinding() = FragmentUpcomingBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = UpcomingMovieItemAdapter()
        binding.recyclerViewUpcomingList.adapter = adapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewUpcomingList)

        val dummyList = listOf(
            UpcomingMovieItem(
                id = 0,
                posterPath = "",
                title = "myTitle",
                releaseDate = "date",
            ),
            UpcomingMovieItem(
                id = 1,
                posterPath = "",
                title = "myTitleeee",
                releaseDate = "dateee",
            )
        )
        adapter.submitList(dummyList)
    }

    companion object {
        fun newInstance() = UpcomingFragment()
    }
}