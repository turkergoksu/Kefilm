![](https://img.shields.io/badge/minSdkVersion-23-brightgreen.svg)
# 🎬Kefilm 
## 📝About
**Kefilm** is a movie app to learn and practice Modern Android development tools. Movie data provided by [The Movie Database (TMDb)](https://www.themoviedb.org/) API.

To run the app you need to get your own API key from [The Movie Database (TMDb)](https://www.themoviedb.org/). Then you need to change the line as indicated below in `app/src/main/cpp/api-keys.cpp`  file.
```cpp
std::string movieDbApiKey = "YOUR_API_KEY_GOES_HERE";
```

## 🌟Todos
+ Add TMDb logo to images.

## ✨Latest Screenshots
| Upcoming | Top Rated |  Popular |
|:-:|:-:|:-:|
| ![U](screenshots/latest/upcoming.gif?raw=true) | ![T](screenshots/latest/toprated.gif?raw=true) | ![P](screenshots/latest/popular.gif?raw=true) |
| Movie Details | |  People Details |
| ![M](screenshots/latest/movie_details.gif?raw=true) | | ![Pe](screenshots/latest/people_details.gif?raw=true) |

## 🛠️Build With 
+ [Retrofit](https://github.com/square/retrofit) - Type-safe HTTP client for Android.
+ [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back.
+ [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling
+ [Glide Transformations](https://github.com/wasabeef/glide-transformations) - An Android transformation library providing a variety of image transformations for Glide. 
+ [ViewPager2](https://developer.android.com/training/animation/vp2-migration) - ViewPager2 is an improved version of the ViewPager library that offers enhanced functionality and addresses common difficulties with using ViewPager.
+ [PercentageView](https://github.com/turkergoksu/PercentageView) - Adjustable custom library to show average score of a movie.
+ [PhotoView](https://github.com/Baseflow/PhotoView) - An Android library that supports zooming and various touch gestures.
+ [Android Jetpack](https://developer.android.com/jetpack) - Android Jetpack is a set of components, tools and guidance to make great Android apps.
  + [Navigation Component](https://developer.android.com/guide/navigation) - Handle everything needed for in-app navigation.
  + [Architecture Component](https://developer.android.com/topic/libraries/architecture) - Android architecture components are a collection of libraries that helps you to manage your UI component lifecycle and handling data persistence. It provides components like LiveData, ViewModel, Paging and ViewBinding which is also used in this project.