package me.turkergoksu.kefilm.v1.ui.peopledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.FragmentPeopleDetailsBinding
import me.turkergoksu.kefilm.v1.Constants
import me.turkergoksu.kefilm.v1.ui.media.SharedViewModel
import me.turkergoksu.kefilm.v1.ui.moviedetails.MediaAdapter
import me.turkergoksu.kefilm.v1.utils.StringUtil

class PeopleDetailsFragment : Fragment() {

    private var mediaAdapter: MediaAdapter? = null
    private var knownMoviesAdapter: KnownMoviesAdapter? = null
    private var binding: FragmentPeopleDetailsBinding? = null
    private val peopleDetailsViewModel: PeopleDetailsViewModel by viewModels()

    private var peopleId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPeopleDetailsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPeopleDetailsFragment()
    }

    private fun setPeopleDetailsFragment() {
        if (requireArguments().isEmpty.not()) {
            peopleId = requireArguments().getInt(Constants.PEOPLE_DETAILS_PEOPLE_ID_ARG_KEY)

            setPeopleDetails()
            setPeopleMedia()
            setPeopleKnownMovies()

            binding?.imageViewBackIcon?.setOnClickListener {
                Navigation.findNavController(it).popBackStack()
            }
        }
    }

    private fun setPeopleKnownMovies() {
        peopleDetailsViewModel.getPeopleKnownMovieListLiveData(peopleId)
            .observe(viewLifecycleOwner, Observer { knownMovieList ->
                knownMoviesAdapter = KnownMoviesAdapter(knownMovieList)
                binding?.recyclerViewKnownMovies?.adapter = knownMoviesAdapter
            })
    }

    private fun setPeopleMedia() {
        peopleDetailsViewModel.getPeopleMediaListLiveData(peopleId)
            .observe(viewLifecycleOwner, Observer { imageList ->
                mediaAdapter = MediaAdapter(imageList) { position ->
                    val sharedViewModel =
                        ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
                    sharedViewModel.setBackdropList(imageList)
                    sharedViewModel.setPosition(position)

                    findNavController().navigate(R.id.mediaFragment)
                }
                binding?.recyclerViewPeopleMedia?.adapter = mediaAdapter
            })
    }

    private fun setPeopleDetails() {
        peopleDetailsViewModel.getPeopleDetailsLiveData(peopleId)
            .observe(viewLifecycleOwner, Observer { peopleDetails ->
                binding?.apply {
                    // Set people name
                    textViewPeopleName.text = peopleDetails.name

                    // Set people photo
                    if (peopleDetails.profilePath != null) {
                        Glide.with(requireContext()).load(
                            "%s%s".format(
                                Constants.API_IMAGE_URL,
                                peopleDetails.profilePath
                            )
                        ).into(imageViewPeoplePhoto)
                    }

                    // Set known for
                    textViewKnownFor.text = peopleDetails.knownForDepartment

                    // Set birthday
                    textViewBirthday.text = peopleDetails.birthday?.let {
                        StringUtil.formatDate(
                            it,
                            Constants.MOVIE_DB_DATE_FORMAT,
                            Constants.PEOPLE_DETAILS_DATE_FORMAT
                        )
                    }

                    // Set place of birth
                    textViewPlaceOfBirth.text = peopleDetails.placeOfBirth

                    // Set bio
                    textViewPeopleBio.text = peopleDetails.biography
                }
            })
    }
}