package me.turkergoksu.kefilm.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.ui.popular.PopularFragment
import me.turkergoksu.kefilm.ui.toprated.TopRatedFragment
import me.turkergoksu.kefilm.ui.upcoming.UpcomingFragment


/**
 * Created by turkergoksu on 29-Mar-20, 12:54 AM
 */
class TopBarPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var fragments = arrayOf(
        UpcomingFragment(),
        TopRatedFragment(),
        PopularFragment()
    )

    override fun getItemCount(): Int = Constants.TOP_BAR_TAB_COUNT

    override fun createFragment(position: Int): Fragment = fragments[position]

}