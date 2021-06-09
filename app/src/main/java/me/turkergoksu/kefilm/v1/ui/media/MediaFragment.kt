package me.turkergoksu.kefilm.v1.ui.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.PagerSnapHelper
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.FragmentMediaBinding

class MediaFragment : DialogFragment() {

    private var binding: FragmentMediaBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            STYLE_NORMAL,
            R.style.FullScreenDialogStyle
        );
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMediaBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get backdropList
        val sharedViewModel =
            ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedViewModel.backdropList.observe(viewLifecycleOwner, Observer { backdropList ->
            binding?.recyclerViewMedia?.adapter = ImageAdapter(backdropList)
        })
        sharedViewModel.position.observe(viewLifecycleOwner, Observer { position ->
            binding?.recyclerViewMedia?.scrollToPosition(position)
        })

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding?.recyclerViewMedia)

        binding?.imageViewCloseIcon?.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}