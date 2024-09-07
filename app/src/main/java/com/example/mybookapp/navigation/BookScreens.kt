package com.example.mybookapp.navigation

import java.lang.IllegalArgumentException

//www.google.com/sign_in
enum class BookScreens {
    HomeScreen,
    DetailScreen;
    companion object {
        fun fromRoute(route: String?): BookScreens
                = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}