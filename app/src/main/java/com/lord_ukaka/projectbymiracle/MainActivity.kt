package com.lord_ukaka.projectbymiracle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lord_ukaka.projectbymiracle.presentation.Screen
import com.lord_ukaka.projectbymiracle.presentation.login.LoginScreen
import com.lord_ukaka.projectbymiracle.presentation.photos.PhotosScreen
import com.lord_ukaka.projectbymiracle.ui.theme.ProjectByMiracleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectByMiracleTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.LoginScreen.route
                ) {
                    composable(route = Screen.LoginScreen.route) {
                        LoginScreen(navController = navController)
                    }
                    composable(route = Screen.PhotosScreen.route) {
                        PhotosScreen(navController = navController)
                    }
                }
            }
        }
    }
}