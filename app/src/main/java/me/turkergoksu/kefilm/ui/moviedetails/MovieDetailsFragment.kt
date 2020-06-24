package me.turkergoksu.kefilm.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.databinding.FragmentMovieDetailsBinding
import me.turkergoksu.kefilm.utils.StringUtil
import java.text.SimpleDateFormat

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailsFragment : Fragment() {

    private val castAdapter = CastAdapter()
    private val mediaAdapter = MediaAdapter()
    private val similarMoviesAdapter = SimilarMoviesAdapter()
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieDetails()

        binding.imageViewBackIcon.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        binding.imageViewPlayIcon.setOnClickListener {
            // TODO: 15-Jun-20 start youtube app with videos link
        }
    }

    private fun setMovieDetails() {
        if (!requireArguments().isEmpty) {
            val movieId = requireArguments().getInt(Constants.MOVIE_DETAILS_MOVIE_ID_ARG_KEY)

            setMovieDetails(movieId)
            setMovieCast(movieId)
            setMovieMedia(movieId)
            setMovieSimilarMovies(movieId)
        }
    }

    private fun setMovieDetails(movieId: Int) {
        movieDetailsViewModel.getMovieDetailsLiveData(movieId)
            .observe(viewLifecycleOwner, Observer { movieDetails ->
                // Set poster image
                Glide.with(requireContext()).load(
                    "%s%s".format(
                        Constants.API_IMAGE_URL,
                        movieDetails.posterPath
                    )
                ).into(binding.imageViewMoviePoster)

                // Set release year
                val releaseYear = StringUtil.getYearFromDate(
                    movieDetails.releaseDate,
                    SimpleDateFormat(Constants.MOVIE_DB_DATE_FORMAT)
                )
                binding.textViewMovieReleaseYear.text = releaseYear

                // Set title
                binding.textViewMovieTitle.text = movieDetails.title

                // Set screen time
                binding.textViewMovieScreenTime.text = "%s min".format(movieDetails.runtime)

                // Set budget
                binding.textViewMovieBudget.text = "$%s".format(movieDetails.budget)

                // TODO: 15-Jun-20 Set vote average

                // Set vote count
                binding.textViewMovieVoteCount.text =
                    "/ %s voted".format(movieDetails.voteCount)

                // Set overview
                binding.textViewMovieOverview.text = movieDetails.overview
            })
    }

    private fun setMovieCast(movieId: Int) {
        binding.recyclerViewMovieCast.adapter = castAdapter
        movieDetailsViewModel.getMovieCastLiveData(movieId)
            .observe(viewLifecycleOwner, Observer { castList ->
                castAdapter.setCastItemList(castList)
            })
    }

    private fun setMovieMedia(movieId: Int) {
        binding.recyclerViewMovieMedia.adapter = mediaAdapter
        movieDetailsViewModel.getMovieBackdropListLiveData(movieId)
            .observe(viewLifecycleOwner, Observer { backdropList ->
                mediaAdapter.setBackdropItemList(backdropList)
            })
    }

    private fun setMovieSimilarMovies(movieId: Int) {
        binding.recyclerViewSimilarMovies.adapter = similarMoviesAdapter
        movieDetailsViewModel.getSimilarMovieListLiveData(movieId)
            .observe(viewLifecycleOwner, Observer { similarMovieList ->
                similarMoviesAdapter.setSimilarMovieList(similarMovieList)
            })
    }
}