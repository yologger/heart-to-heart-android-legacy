# Heart to Heart for Android

## Warning
This project will be deprecated soon. You can download new project based on `Gradle multiple modules` [here](https://github.com/yologger/heart-to-heart-android).

## Introduction
`Heart to Heart` is a SNS application. This app is similar to Facebook. You can also download Heart to Heart for <u>iOS</u> [here](https://github.com/yologger/heart_to_heart_ios). 

## Screenshots
* Sign up, Log in
<img src="/imgs/hth_auth.gif" width="200">
  
* Create new post
<img src="/imgs/hth_new_post.gif" width="200">
  
* Follow, unfollow
<img src="/imgs/hth_follow.gif" width="200">
  
* Log out
<img src="/imgs/hth_log_out.gif" width="200">

## Architecture
* MVVM
* Clean Architecture

## Prerequisite
You have to set up server-side application to run this. You can download [here](https://github.com/yologger/heart_to_heart_server).

## Dependencies
Heart to Heart for Android has following dependencies:
* Kotlin
* [Koin](https://github.com/InsertKoinIO/koin) for DI
* Android Architecture Component - Data Binding, ViewModel, LiveData
* Android Navigation Component
* [RxKotlin](hhttps://github.com/ReactiveX/RxKotlin)
* [OkHttp3](https://github.com/square/okhttp)
* [Retrofit2](https://github.com/square/retrofit)
* [Gson](https://github.com/google/gson)
* [TedImagePicker](https://github.com/ParkSangGwon/TedImagePicker)
* [Image Sldier](https://github.com/ouattararomuald/android-image-slider)
* [Floating Action Button Speed Dial](https://github.com/leinardi/FloatingActionButtonSpeedDial)


## Features
#### `Implemented`
* Authorization (OAuth 2.0)
* Create new post
	- Choose and upload multiple images 
* Show all posts
    - Infinite scrolling
* Upload avatar image

#### `Not Implemented Yet`
* Search
* Follow user
* Like post