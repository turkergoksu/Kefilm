package me.turkergoksu.kefilm

object ApiKeyLibrary {
    init {
        System.loadLibrary(Constants.EXTENSION_API_KEYS_FILE_NAME)
    }

    // Name of the function must be same as used in the native code.
    external fun getMovieDbApiKeyFromJNI(): String
}