package me.turkergoksu.kefilm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*

import me.turkergoksu.kefilm.R

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    private lateinit var mPagerAdapter: TopBarPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPagerAdapter = TopBarPagerAdapter(this)
        viewpager.adapter = mPagerAdapter

        TabLayoutMediator(tabLayout, viewpager) { tabs, position ->
            tabs.text = resources.getStringArray(R.array.tab_titles)[position]
        }.attach()
    }

}
