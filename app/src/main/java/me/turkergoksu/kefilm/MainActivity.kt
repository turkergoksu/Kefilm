package me.turkergoksu.kefilm

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import me.turkergoksu.kefilm.common.navigation.KefilmNavigation
import me.turkergoksu.kefilm.common.navigation.Screen
import me.turkergoksu.kefilm.theme.KefilmTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val bottomNavigationItems = listOf(
        Screen.NowPlaying,
    )

    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KefilmTheme {
                KefilmNavigation(
                    navHostController = rememberNavController(),
                    bottomNavigationItems = bottomNavigationItems
                )
            }
        }
    }
}