package me.turkergoksu.kefilm

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import me.turkergoksu.kefilm.common.navigation.KefilmNavigation
import me.turkergoksu.kefilm.theme.KefilmTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KefilmTheme {
                KefilmNavigation()
            }
        }
    }
}