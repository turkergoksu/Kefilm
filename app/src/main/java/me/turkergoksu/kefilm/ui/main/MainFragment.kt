package me.turkergoksu.kefilm.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator

import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.data.remote.api.MovieServiceProvider
import me.turkergoksu.kefilm.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var mPagerAdapter: TopBarPagerAdapter
    private val movieServiceProvider = MovieServiceProvider()
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

    companion object {
        private val TAG = MainFragment::class.java.simpleName
    }

}
