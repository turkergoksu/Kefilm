package me.turkergoksu.kefilm

object ApiKeyLibrary {
    init {
        System.loadLibrary(Constants.extensionApiKeysFileName)
    }

    // Name of the function must be same as used in the native code.
    external fun getMovieDbApiKeyFromJNI(): String
}