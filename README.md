<a href="https://hack36.com"> <img src="http://bit.ly/BuiltAtHack36" height=20px> </a>


## Introduction:
  ![](media/design.jpg)

# **Raksha** 

**Raksha** is app in which users can see the location of the place or people he/she add so that in case of emergency he/she can reach the nearest place or people and stay there. This app demonstrates use of *Modern Android development* tools.

[![Raksha](https://img.shields.io/badge/Raksha✅-APK-red.svg?style=for-the-badge&logo=android)](https://github.com/nishantsharma1115/Raksha/blob/master/testApk/raksha.apk)

## About

 It uses ROOM database i.e. Phone storage as its backend. The main aim to use the phone storage is **Your data is completely safe in your phone**
 
- Fully functionable. 
- Clean and Simple Material UI.
  
## Demo Video Link:
  <a href="https://youtu.be/980R-vV2Inw">https://youtu.be/980R-vV2Inw</a>
  
## Presentation Link:
  <a href="https://docs.google.com/presentation/d/1k2OKHzWCKl8xSXMUHSt3jC18sVlyR2arwK7Yl8QDRnU/edit?usp=sharing"> https://docs.google.com/presentation/d/1k2OKHzWCKl8xSXMUHSt3jC18sVlyR2arwK7Yl8QDRnU/edit?usp=sharing </a>
  
  
## Table of Contents:
   1) Splash Screen 
   2) Onboarding Process
   3) HomeScreen in which users can see all saved SafeHouses
   4) A map screen which shows all safehouses in a map, which shows users exact location
   5) A screen to add SafeHouses.
   6) A map screen to select address of SafeHouse for saving it

## 📸 Screenshots

 ||||
 |:----------------------------------------:|:-----------------------------------------:|:-----------------------------------------: |
 | ![](media/SPlashScreen.jpg) | ![](media/Onboarding1.jpg) | ![](media/Onbording2.jpg) |
 | ![](media/Onbording3.jpg) | ![](media/homescreen.jpg) | ![](media/addsafehouse.jpg)

 ## Built With 🛠
 - [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
 - [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
 - [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
   - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
   - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
   - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
   - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - Binds data directly into XML layouts
 - [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
   - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
   - [Hilt-ViewModel](https://developer.android.com/training/dependency-injection/hilt-jetpack) - DI for injecting `ViewModel`.
 - [Backend](https://https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase) - Room Database for Android
 - [Google Maps](https://developers.google.com/maps/documentation) - To render google maps in app
 - [Timber](https://github.com/JakeWharton/timber) - A simple logging library for android.
 - [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

 # Package Structure

     com.baldeagles.raksha    # Root Package
     .
     ├── data                # For data handling.
     │   ├── model           # Model data classes 
     │   └── repository      # Single source of data.
     |
     ├── di                  # Dependency Injection             
     │   └── module          # DI Modules
     |
     ├── ui                  # UI/View layer
     │   ├── viewmodels      # All viewmodels
     |   ├── fragments        # All fragments
     │   └── adapters        # All recyclerView adapters 
     |
     └── utils               # Utility Classes / Kotlin extensions


 ## Architecture
 This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

 ![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

## Technology Stack:
  1) Kotlin
  2) XML
  3) Android Jetpack
  4) Android Architecture Components
  5) Google Maps SDK
  6) SQLite database using room
  7) Adobe XD
  

## Contributors:

Team Name: BaldEagles

* [Nishant Sharma](https://github.com/nishantsharma1115)
* [Vaibhav Jaiswal](https://github.com/Vaibhav2002)
* [Sagar Khurana](https://github.com/hellosagar)


### Made at:
<a href="https://hack36.com"> <img src="http://bit.ly/BuiltAtHack36" height=20px> </a>
