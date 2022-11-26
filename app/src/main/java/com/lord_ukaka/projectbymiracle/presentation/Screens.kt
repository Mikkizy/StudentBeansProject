package com.lord_ukaka.projectbymiracle.presentation

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login")
    object PhotosScreen: Screen("photos")
}
