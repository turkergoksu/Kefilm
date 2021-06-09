package me.turkergoksu.kefilm.v1.ui.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import me.turkergoksu.kefilm.databinding.FragmentTopRatedBinding
import me.turkergoksu.kefilm.v1.ui.GenreViewModel
import me.turkergoksu.kefilm.v1.ui.upcoming.OnUpcomingFragmentEventListener

/**
 * A simple [Fragment] subclass.
 */
class TopRatedFragment : Fragment() {

    private lateinit var adapter: TopRatedMovieAdapter
    private val topRatedViewModel: TopRatedViewModel by viewModels()
    private val genreViewModel: GenreViewModel by viewModels()

    internal lateinit var onUpcomingFragmentEventListener: OnUpcomingFragmentEventListener

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

        topRatedViewModel.getPagedListLiveData()
            .observe(viewLifecycleOwner,
                Observer {
                    // Create adapter
                    adapter = TopRatedMovieAdapter()

                    // Set adapter's list
                    adapter.submitList(it)

                    // Set adapter to RecyclerView
                    binding.recyclerViewTopRatedMovies.adapter = adapter

                    // Hide loading bar
                    binding.progressBarLoading.hide()
                })

        genreViewModel.getGenreHashMapLiveData().observe(viewLifecycleOwner, Observer { genres ->
            adapter.setGenreMap(genres)
        })
    }

//    fun setOnUpcomingFragmentEventListener(onUpcomingFragmentEventListener: OnUpcomingFragmentEventListener) {
//        this.onUpcomingFragmentEventListener = onUpcomingFragmentEventListener
//    }

    override fun onResume() {
        super.onResume()
        // TODO bu call'u UpcomingFragment'ın onDetach veya onStop fonklarının icine koymak daha mantıklı
        // Reset background to default color
        onUpcomingFragmentEventListener.onUpcomingFragmentStop()
    }

}
