package me.turkergoksu.kefilm.movie_detail.ui

import me.turkergoksu.kefilm.Constants
import me.turkergoksu.kefilm.movie_detail.data.MovieDetail

data class MovieDetailViewState(val movieDetail: MovieDetail) {

    fun getRating() = movieDetail.voteAverage.toFloat() / 2f

    fun getVoteCountText() = String.format("(%s)", movieDetail.voteCount)

    fun getTitle() = movieDetail.title

    fun getYear() = movieDetail.releaseYear

    fun getPosterUrl() = Constants.API_IMAGE_URL + movieDetail.posterPath

    fun getOverview() = movieDetail.overview
}