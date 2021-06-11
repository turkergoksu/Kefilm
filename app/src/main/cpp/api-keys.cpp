/**
 * Change this file name to "api-key.cpp"
 */

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_me_turkergoksu_kefilm_ApiKeyLibrary_getMovieDbApiKeyFromJNI(JNIEnv *env, jobject object) {
    std::string movieDbApiKey = "YOUR_API_KEY_GOES_HERE";
    return env->NewStringUTF(movieDbApiKey.c_str());
}