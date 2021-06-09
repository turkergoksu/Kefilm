package me.turkergoksu.kefilm.upcoming.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import me.turkergoksu.kefilm.core.BaseFragment
import me.turkergoksu.kefilm.databinding.FragmentUpcomingBinding

/**
 * Created by turkergoksu on 09-Jun-21.
 */
class UpcomingFragment : BaseFragment<FragmentUpcomingBinding, UpcomingViewModel>() {

    override val viewModel: UpcomingViewModel by viewModels()

    override fun getViewBinding() = FragmentUpcomingBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}