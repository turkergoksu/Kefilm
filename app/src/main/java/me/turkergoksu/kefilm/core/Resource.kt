package me.turkergoksu.kefilm.core

/**
 * Created by turkergoksu on 11-Jun-21.
 */
sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val message: String? = null, val data: T? = null) : Resource<T>()
    class Loading<T>(val data: T? = null) : Resource<T>()

    fun <R> mapData(transform: (T) -> R): Resource<R> = when (this) {
        is Success -> Success(data = transform(data))
        is Error -> Error(message = message, data = data?.let { transform(it) })
        is Loading -> Loading(data = data?.let { transform(it) })
    }
}