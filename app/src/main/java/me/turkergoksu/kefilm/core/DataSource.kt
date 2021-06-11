package me.turkergoksu.kefilm.core

import retrofit2.Response

/**
 * Created by turkergoksu on 11-Jun-21.
 */
interface DataSource {

    interface Remote {
        fun <RESPONSE> Response<RESPONSE>?.asResource(): Resource<RESPONSE> {
            return when (this?.isSuccessful) {
                true -> Resource.Success(data = body()!!)
                else -> Resource.Error(this?.errorBody().toString())
                // FIXME: 11-Jun-21 Check later to provide better error message
            }
        }
    }
}