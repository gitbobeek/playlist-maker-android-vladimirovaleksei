package com.practicum.playlist_maker_android_vladimirovaleksei.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.playlist_maker_android_vladimirovaleksei.MainScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.TrackRepositoryImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.FavoriteScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.LibraryScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.search.SearchScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.SettingsScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.search.SearchViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.search.SearchViewModelFactory

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

            val searchViewModel: SearchViewModel = viewModel(
                factory = SearchViewModelFactory(
                    trackRepository = TrackRepositoryImpl()
                )
            )
            SearchScreen(
                modifier = Modifier,
                searchViewModel = searchViewModel,
                onClick = { navController.popBackStack() }
            )
        }

        composable(route = Routes.Settings.route) {
            SettingsScreen {
                navController.popBackStack()
            }
        }
    }
}
