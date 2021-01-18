# Heart to Heart for Android

## Introduction
**Heart to Heart** is a SNS application. This app is similar to Facebook. You can also download [Heart to Heart for iOS](https://github.com/yologger/heart_to_heart_ios). Heart to heart is implementing MVVM with Clean Architecture.

## Screenshots
<img src="/imgs/hth_auth.gif" width="200">
<img src="/imgs/hth_new_post.gif" width="200">
<img src="/imgs/hth_follow.gif" width="200">
<img src="/imgs/hth_log_out.gif" width="200">


![](/imgs/hth_auth.gif){: width="200" height="400"}
![](/imgs/hth_new_post.gif){: width="200" height="400"}
![](/imgs/hth_follow.gif){: width="200" height="400"}
![](/imgs/hth_log_out.gif){: width="200" height="400"}


## Prerequisite
You have to set up server-side application to run this. You can download [here](https://github.com/yologger/heart_to_heart_server).

## Dependencies
Heart to Heart for Android has following dependencies:
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
* Sign up
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
* Find password
* Change password

