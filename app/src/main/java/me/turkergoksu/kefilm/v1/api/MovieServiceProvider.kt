package me.turkergoksu.kefilm.v1.api

import me.turkergoksu.kefilm.v1.ApiKeyLibrary
import me.turkergoksu.kefilm.v1.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by turkergoksu on 30-Mar-20, 7:39 PM
 */

class MovieServiceProvider {

    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url()
            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", ApiKeyLibrary.getMovieDbApiKeyFromJNI()).build()

            val newRequest = originalRequest.newBuilder().url(newUrl).build()
            chain.proceed(newRequest)
        }

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    val movieService : MovieService = retrofit.create(MovieService::class.java)
}