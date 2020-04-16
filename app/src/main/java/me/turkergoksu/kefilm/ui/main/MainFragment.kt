package me.turkergoksu.kefilm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import me.turkergoksu.kefilm.Constants

import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.databinding.FragmentMainBinding
import me.turkergoksu.kefilm.ui.toprated.TopRatedFragment
import me.turkergoksu.kefilm.ui.upcoming.UpcomingFragment
import me.turkergoksu.kefilm.utils.ImageLoadingUtil
import me.turkergoksu.kefilm.ui.upcoming.OnUpcomingFragmentEventListener

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var mPagerAdapter: TopBarPagerAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mPagerAdapter = TopBarPagerAdapter(this)
        binding.viewpager.adapter = mPagerAdapter

        // Disable swipe
        binding.viewpager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tabs, position ->
            tabs.text = resources.getStringArray(R.array.tab_titles)[position]
        }.attach()
    }

    override fun onAttachFragment(childFragment: Fragment) {
        if (childFragment is UpcomingFragment) {
            // Set OnUpcomingItemChangeListener and override onItemChange
            childFragment.onUpcomingFragmentEventListener =
                object : OnUpcomingFragmentEventListener {
                    override fun onItemChange(posterPath: String) {
                        ImageLoadingUtil.changeImageWithCrossFadeTransition(
                            context!!,
                            binding.imageViewMainFragment,
                            posterPath,
                            Constants.UPCOMING_CROSS_FADE_TRANSITION_DURATION
                        )
                    }

                    override fun onUpcomingFragmentStop() {
                        TODO("Not yet implemented")
                    }
                }
        } else if (childFragment is TopRatedFragment) {
            childFragment.onUpcomingFragmentEventListener =
                object : OnUpcomingFragmentEventListener {
                    override fun onItemChange(posterPath: String) {
                        TODO("Not yet implemented")
                    }

                    override fun onUpcomingFragmentStop() {
                        ImageLoadingUtil.resetMainFragmentBackground(
                            context!!,
                            binding.imageViewMainFragment
                        )
                    }
                }
        }
    }

    companion object {
        private val TAG = MainFragment::class.java.simpleName
    }

}
