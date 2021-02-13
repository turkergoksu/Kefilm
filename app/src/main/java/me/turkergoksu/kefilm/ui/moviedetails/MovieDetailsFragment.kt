package me.turkergoksu.kefilm.ui.moviedetails

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.FragmentMovieDetailsBinding
import me.turkergoksu.kefilm.ui.media.SharedViewModel
import me.turkergoksu.kefilm.utils.StringUtil

/**
 * A simple [Fragment] subclass.
 * Use the [MovieDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieDetailsFragment : Fragment() {

    private lateinit var castAdapter: CastAdapter
    private lateinit var mediaAdapter: MediaAdapter
    private lateinit var similarMoviesAdapter: SimilarMoviesAdapter
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()

    private lateinit var binding: FragmentMovieDetailsBinding

    private var movieId = 0

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

        setMovieDetailsFragment()

        binding.imageViewBackIcon.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

    private fun setMovieDetailsFragment() {
        if (!requireArguments().isEmpty) {
            movieId = requireArguments().getInt(Constants.MOVIE_DETAILS_MOVIE_ID_ARG_KEY)

            setMovieDetails()
            setMovieCast()
            setMovieMedia()
            setMovieSimilarMovies()
            setPlayVideo()
        }
    }

    private fun setPlayVideo() {
        // Set play video
        binding.imageViewPlayIcon.setOnClickListener {
            movieDetailsViewModel.getMovieVideoList(movieId)
                .observe(viewLifecycleOwner, Observer { videoList ->
                    if (videoList.isEmpty().not()) {
                        for (video in videoList) {
                            if (isVideoFromYoutubeAndTrailer(
                                    video.site,
                                    video.type,
                                    video.key
                                )
                            ) {
                                video.key?.let { videoKey ->
                                    getConfirmationFromUserToOpenYoutubeApp(
                                        videoKey = videoKey
                                    )
                                }
                                break
                            }
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            R.string.movie_video_not_found,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }

    private fun isVideoFromYoutubeAndTrailer(
        videoSite: String?,
        videoType: String?,
        videoKey: String?
    ): Boolean = videoSite == Constants.MOVIE_DETAILS_VIDEO_SITE_YOUTUBE &&
            videoType == Constants.MOVIE_DETAILS_VIDEO_TYPE &&
            videoKey != null


    private fun getConfirmationFromUserToOpenYoutubeApp(videoKey: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.trailer_action_text)
            .setPositiveButton(R.string.trailer_action_pos_button) { dialog, _ ->
                try {
                    val appIntent =
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("%s%s".format(Constants.YOUTUBE_URL, videoKey))
                        )
                    startActivity(appIntent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        requireContext(),
                        R.string.trailer_error_text,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton(R.string.trailer_action_neg_button) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun setMovieDetails() {
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
                    Constants.MOVIE_DB_DATE_FORMAT
                )
                binding.textViewMovieReleaseYear.text = releaseYear

                // Set title
                binding.textViewMovieTitle.text = movieDetails.title

                // Set screen time
                binding.textViewMovieScreenTime.text = "%s min".format(movieDetails.runtime)

                // Set budget
                binding.textViewMovieBudget.text = "$%s".format(movieDetails.budget)

                // Set vote average
                binding.percentageViewMovieAvg.setPercentage((movieDetails.voteAverage * 10).toInt())

                // Set vote count
                binding.textViewMovieVoteCount.text =
                    "/ %s voted".format(movieDetails.voteCount)

                // Set overview
                binding.textViewMovieOverview.text = movieDetails.overview
            })
    }

    private fun setMovieCast() {
        movieDetailsViewModel.getMovieCastLiveData(movieId)
            .observe(viewLifecycleOwner, Observer { castList ->
                castAdapter = CastAdapter(castList)
                binding.recyclerViewMovieCast.adapter = castAdapter
            })
    }

    private fun setMovieMedia() {
        movieDetailsViewModel.getMovieBackdropListLiveData(movieId)
            .observe(viewLifecycleOwner, Observer { backdropList ->
                mediaAdapter = MediaAdapter(backdropList) { position ->
                    val sharedViewModel =
                        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
                    sharedViewModel.setBackdropList(backdropList)
                    sharedViewModel.setPosition(position)

                    findNavController().navigate(R.id.mediaFragment)
                }
                binding.recyclerViewMovieMedia.adapter = mediaAdapter
            })
    }

    private fun setMovieSimilarMovies() {
        movieDetailsViewModel.getSimilarMovieListLiveData(movieId)
            .observe(viewLifecycleOwner, Observer { similarMovieList ->
                similarMoviesAdapter = SimilarMoviesAdapter(similarMovieList)
                binding.recyclerViewSimilarMovies.adapter = similarMoviesAdapter
            })
    }
}