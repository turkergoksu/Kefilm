package me.turkergoksu.kefilm.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.turkergoksu.kefilm.ApiKeyLibrary
import me.turkergoksu.kefilm.BuildConfig
import me.turkergoksu.kefilm.api.MovieService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by turkergoksu on 11-Jun-21.
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideMovieService(): MovieService = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttp())
        .build()
        .create(MovieService::class.java)

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient().newBuilder().addInterceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url()

            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", ApiKeyLibrary.getMovieDbApiKeyFromJNI()).build()
            val newRequest = originalRequest.newBuilder().url(newUrl).build()

            chain.proceed(newRequest)
        }.build()
}