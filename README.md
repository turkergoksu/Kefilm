# Kefilm 🎬
## 📝 About
**Kefilm** is a movie app to learn and practice Modern Android development tools. Movie data provided by [The Movie Database (TMDb)](https://www.themoviedb.org/) API.
+ Android 6.0+ (>23)

## 🌟 Todos
#### 🚩 Critical
+ Write a custom view library to show movie rate with percentage.
+ Add TMDb logo to images.

#### 💎 Would Nice
+ Light/dark theme support 🌗.
+ Offline support ⚫.
+ Try to achieve 60 FPS for UpcomingFragment (Maybe research on Glide caching might work?).
+ Try to achieve at least 30+ FPS for TopRatedFragment (Maybe need to remove upper items as user scroll downs? Needs better paging).
+ Work on swipe between upcoming and topRated fragments(Upcoming has horizontal RecyclerView so it might be tricky).
+ Hide the top status bar of device (Which means, run app in fullscreen).
+ Add a transition when movie details comes up (Probably based on poster image).

## ✨ Latest Screenshots
| Upcoming | Top Rated |  Popular |
|:-:|:-:|:-:|
| ![U](screenshots/latest/upcoming.gif?raw=true) | ![T](screenshots/latest/toprated.gif?raw=true) | ![P]() |
| Movie Details | Media |  People Details |
| ![M]() | ![Me]() | ![Pe]() |
| | Search | |
| | ![S]() | |

## ✨ Prototype Screenshots
Designs made with [Figma](https://www.figma.com).

| Upcoming | Top Rated |  Popular |
|:-:|:-:|:-:|
| ![U](screenshots/prototype/p_upcoming.png?raw=true) | ![T](screenshots/prototype/p_top_rated.png?raw=true) | ![P](screenshots/prototype/p_popular.png?raw=true) |
| Movie Details | Media |  People Details |
| ![M](screenshots/prototype/p_movie_details.png?raw=true) | ![Me](screenshots/prototype/p_media.png?raw=true) | ![Pe](screenshots/prototype/p_people_details.png?raw=true) |
| | Search | |
| | ![S](screenshots/prototype/p_search.png?raw=true) | |

## 🛠️ Building With 
+ [Retrofit](https://github.com/square/retrofit) - Type-safe HTTP client for Android.
+ [Gson](https://github.com/google/gson) - A Java serialization/deserialization library to convert Java Objects into JSON and back.
+ [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling
+ [Glide Transformations](https://github.com/wasabeef/glide-transformations) - An Android transformation library providing a variety of image transformations for Glide. 
+ [ViewPager2](https://developer.android.com/training/animation/vp2-migration) - ViewPager2 is an improved version of the ViewPager library that offers enhanced functionality and addresses common difficulties with using ViewPager.
+ [Android Jetpack](https://developer.android.com/jetpack) - Android Jetpack is a set of components, tools and guidance to make great Android apps.
  + [Navigation Component](https://developer.android.com/guide/navigation) - Handle everything needed for in-app navigation.
  + [Architecture Component](https://developer.android.com/topic/libraries/architecture) - Android architecture components are a collection of libraries that helps you to manage your UI component lifecycle and handling data persistence. It provides components like LiveData, ViewModel, Paging and ViewBinding which is also used in this project.