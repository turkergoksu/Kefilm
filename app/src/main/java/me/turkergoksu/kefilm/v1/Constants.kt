package me.turkergoksu.kefilm.v1

import me.turkergoksu.kefilm.R

object Constants {
    const val EXTENSION_API_KEYS_FILE_NAME = "api-keys"

    const val API_URL = "https://api.themoviedb.org/"
    const val API_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    const val YOUTUBE_URL = "https://www.youtube.com/watch?v="

    const val TOP_BAR_TAB_COUNT = 3

    const val MOVIE_DB_DATE_FORMAT = "yyyy-MM-dd"

    // Upcoming
    const val UPCOMING_MOVIE_DATE_FORMAT = "dd MMM yyyy"
    const val UPCOMING_MOVIE_ITEM_BLUR_RADIUS = 25
    const val UPCOMING_MOVIE_ITEM_BLUR_SAMPLING_VALUE = 3
    const val UPCOMING_MOVIE_ITEM_CORNER_RADIUS = 75
    const val UPCOMING_CROSS_FADE_TRANSITION_DURATION = 1000

    // Movie Details
    const val MOVIE_DETAILS_MOVIE_ID_ARG_KEY = "movieId"
    const val MOVIE_DETAILS_MEDIA_ITEM_CORNER_RADIUS = 32
    const val MOVIE_DETAILS_VIDEO_SITE_YOUTUBE = "YouTube"
    const val MOVIE_DETAILS_VIDEO_TYPE = "Trailer"

    // Popular
    const val POPULAR_MOVIE_ITEM_DEFAULT_WIDTH = 192

    const val APP_DEFAULT_BACKGROUND_COLOR = R.color.defaultBackground

    // People Details
    const val PEOPLE_DETAILS_PEOPLE_ID_ARG_KEY = "peopleId"
    const val PEOPLE_DETAILS_DATE_FORMAT = "dd MMM yyyy"
}
