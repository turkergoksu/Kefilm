package me.turkergoksu.kefilm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.trendyol.medusalib.navigator.MultipleStackNavigator
import com.trendyol.medusalib.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import me.turkergoksu.kefilm.databinding.ActivityMainBinding
import me.turkergoksu.kefilm.upcoming.ui.UpcomingFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigator.NavigatorListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val rootFragmentProvider: List<() -> Fragment> =
        listOf(
            { UpcomingFragment.newInstance() },
        )

    val navigator: MultipleStackNavigator =
        MultipleStackNavigator(
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainer,
            rootFragmentProvider = rootFragmentProvider,
            navigatorListener = this
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigator.initialize(savedInstanceState)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_upcoming -> {
                    navigator.switchTab(0)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onTabChanged(tabIndex: Int) {
        when (tabIndex) {
            0 -> binding.bottomNavigationView.selectedItemId = R.id.navigation_upcoming
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        navigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        if (navigator.canGoBack()) navigator.goBack()
        else super.onBackPressed()
    }
}