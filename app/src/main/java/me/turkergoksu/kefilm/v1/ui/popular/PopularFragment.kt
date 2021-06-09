package me.turkergoksu.kefilm.v1.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_main.view.*
import me.turkergoksu.kefilm.databinding.FragmentPopularBinding
import me.turkergoksu.kefilm.v1.ui.upcoming.OnUpcomingFragmentEventListener

/**
 * A simple [Fragment] subclass.
 * Use the [PopularFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PopularFragment : Fragment() {

    private lateinit var adapter: PopularMovieAdapter
    private val popularViewModel: PopularViewModel by viewModels()

    private lateinit var binding: FragmentPopularBinding

    internal lateinit var onUpcomingFragmentEventListener: OnUpcomingFragmentEventListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularViewModel.getPagedListLiveData().observe(viewLifecycleOwner, Observer {
            // Create adapter
            adapter = PopularMovieAdapter()

            // Set adapter's list
            adapter.submitList(it)

            // Set adapter to RecyclerView
            binding.recyclerViewPopularMovies.adapter = adapter

            // Hide loading bar
            binding.progressBarLoading.hide()
        })
    }

    override fun onResume() {
        super.onResume()
        // Reset background to default color
        onUpcomingFragmentEventListener.onUpcomingFragmentStop()
    }
}