# Hilt-MultiModule-Cache
Modular Android architecture which showcase Kotlin, MVVM, Navigation, Hilt, RxJava, Retrofit, DataBinding, Espresso, Unit test and Kotlin Gradle DSL.

## Features
* Modular Android App Architecture.
* MVVM Architecture + Repository design Pattern.
* Jetpack Libraries and Architecture Components.
* Kotlin Gradle DSL.

## Modules

Modules are collection of source files and build settings that allow you to divide a project into discrete units of functionality. In this case apart from dividing by functionality/responsibility, existing the following dependence between them. The project is divided into 4 Modules :

#### App module

The `:app` module is an [com.android.application](https://developer.android.com/studio/build/), which is needed to create the app bundle.

#### Core module

The `:core` module is an [com.android.library](https://developer.android.com/studio/projects/android-library) for serving network requests. Providing the data source for the many features that require it.


#### Common module

The `:common` module is an [com.android.library](https://developer.android.com/studio/projects/android-library) only contains code and resources which are shared between feature modules. Reusing this way resources, layouts, views, and components in the different features modules, without the need to duplicate code.

#### Feature module

The `:feature_list` module is an [com.android.library](https://developer.android.com/studio/projects/android-library) which is a module containing a specific feature, isolated from the rest in accordance with business logic.


## Testing
Local unit testing is done for ViewModel layer in `:feature_list` module.

## Libraries
* [Android Jetpack](https://developer.android.com/jetpack)
   * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) ViewModel is designed to store and manage UI-related data in a lifecycle conscious way. This allows data to survive configuration changes such as screen rotations.
   * [DataBinding](https://developer.android.com/topic/libraries/data-binding/) is a Library in the support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
   * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) is lifecycle-aware, meaning it respects the lifecycle of other app components updating app component observers that are in an active lifecycle state.
   * [Room](https://developer.android.com/topic/libraries/architecture/room) is a library for data storage persistence which provides an abstraction layer over SQLite.
   * [Navigation](https://developer.android.com/guide/navigation/) Android Jetpack's Navigation component helps you implement effective navigation.
   * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
* [RxJava](https://github.com/ReactiveX/RxJava) is a library for composing asynchronous code using observable sequences.
* [RxAndroid](https://github.com/ReactiveX/RxAndroid) is a module that adds the minimum classes to RxJava to make writing reactive components in Android.
* [Retrofit](https://square.github.io/retrofit/) is a Type-safe HTTP client for Android, Java and Kotlin by Square.
* [Moshi](https://github.com/square/moshi) is a modern JSON library for Android and Java. It makes it easy to parse JSON format data.
* [OkHttp interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) Logs HTTP requests and responses.
* [Material Design](https://material.io/develop/android/) Build beautiful, usable products using Material Components for Android
* [Mockito](https://github.com/mockito/mockito) which is the most popular Mocking framework for unit tests written in Java as well as Kotlin.
* [Espresso](https://developer.android.com/training/testing/espresso) Automated testing UI test

## Licence
    MIT License

    Copyright (c) 2021 Mohammadali Rezaei

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
