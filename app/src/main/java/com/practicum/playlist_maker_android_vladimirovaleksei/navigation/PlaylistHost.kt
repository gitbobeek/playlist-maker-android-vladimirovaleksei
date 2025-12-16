package com.practicum.playlist_maker_android_vladimirovaleksei.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.playlist_maker_android_vladimirovaleksei.MainScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.FavoriteScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.LibraryScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.SearchScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.SettingsScreen

@Composable
fun PlaylistHost() {
    val navController = rememberNavController()
    val home = Routes.Main.route

    NavHost(
        navController = navController,
        startDestination = home
    ) {
        composable(route = home) {
            MainScreen(
                onSearch = { navController.navigate(Routes.Search.route) },
                onPlaylists = { navController.navigate(Routes.Library.route) },
                onFavorite = { navController.navigate(Routes.Favorite.route) },
                onSettings = { navController.navigate(Routes.Settings.route) },
            )
        }

        composable(route = Routes.Favorite.route) {
            FavoriteScreen()
        }

        composable(route = Routes.Library.route) {
            LibraryScreen()
        }

        composable(route = Routes.Search.route) {
            SearchScreen {
                navController.popBackStack()
            }
        }

        composable(route = Routes.Settings.route) {
            SettingsScreen {
                navController.popBackStack()
            }
        }
    }
}
