#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_me_turkergoksu_kefilm_ApiKeyLibrary_getMovieDbApiKeyFromJNI(JNIEnv *env, jobject object) {
    std::string movieDbApiKey = "TEST";
    return env->NewStringUTF(movieDbApiKey.c_str());
}