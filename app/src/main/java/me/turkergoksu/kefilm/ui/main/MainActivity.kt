package me.turkergoksu.kefilm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import me.turkergoksu.kefilm.R
import me.turkergoksu.kefilm.model.genre.Genre
import me.turkergoksu.kefilm.ui.GenreViewModel

class MainActivity : AppCompatActivity() {

    private val genreListViewModel: GenreViewModel by viewModels()
    var genreMap = mapOf<Int, Genre>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load genres
        genreListViewModel.getGenreHashMapLiveData()
            .observe(this, Observer { genres ->
                genreMap = genres
            })
    }
}
