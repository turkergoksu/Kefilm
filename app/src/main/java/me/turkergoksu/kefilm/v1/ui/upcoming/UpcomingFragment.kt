package me.turkergoksu.kefilm.v1.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import me.turkergoksu.kefilm.databinding.FragmentUpcomingBinding
import me.turkergoksu.kefilm.v1.model.upcoming.UpcomingMovieItem

/**
 * A simple [Fragment] subclass.
 */
class UpcomingFragment : Fragment() {

    private lateinit var adapter : UpcomingMovieAdapter
    private val upcomingViewModel: UpcomingViewModel by viewModels()
    private lateinit var scrollListener: CustomScrollListener
    internal lateinit var onUpcomingFragmentEventListener: OnUpcomingFragmentEventListener

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

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerViewUpcomingMovies)

        layoutManager = binding.recyclerViewUpcomingMovies.layoutManager as LinearLayoutManager

        scrollListener = CustomScrollListener(layoutManager, onUpcomingFragmentEventListener)
        binding.recyclerViewUpcomingMovies.addOnScrollListener(scrollListener)

        upcomingViewModel.getUpcomingMovieListLiveData()
            .observe(viewLifecycleOwner, Observer { upcomingMovieList ->
                // Initialize adapter
                adapter = UpcomingMovieAdapter(upcomingMovieList)

                // Set adapter to RecyclerView
                binding.recyclerViewUpcomingMovies.adapter = adapter

                this.upcomingMovieList = upcomingMovieList

                // Set listener's movie list
                scrollListener.setUpcomingMovieList(upcomingMovieList)

                // Set background image for first item
                onUpcomingFragmentEventListener.onItemChange(upcomingMovieList.first().posterPath)

                // Hide progress bar
                binding.progressBarLoading.hide()
            })
    }

    override fun onResume() {
        super.onResume()
        // Set background image
        if (upcomingMovieList.isNotEmpty()) {
            onUpcomingFragmentEventListener
                .onItemChange(upcomingMovieList[scrollListener.currentMovieItemIndex].posterPath)
        }
    }

}
