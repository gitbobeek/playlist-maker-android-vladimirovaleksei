package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.playlist_maker_android_vladimirovaleksei.MainScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.data.PlaylistRepositoryImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.TrackRepositoryImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.FavoriteScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists.PlaylistsScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.search.SearchScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.SettingsScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists.PlaylistsViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists.PlaylistsViewModelFactory
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.search.SearchViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.search.SearchViewModelFactory

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
                onPlaylists = { navController.navigate(Routes.Playlists.route) },
                onFavorite = { navController.navigate(Routes.Favorite.route) },
                onSettings = { navController.navigate(Routes.Settings.route) },
            )
        }

        composable(route = Routes.Favorite.route) {
            FavoriteScreen()
        }

        composable(route = Routes.Playlists.route) {
            val playlistsViewModel: PlaylistsViewModel = viewModel(
                factory = PlaylistsViewModelFactory(
                    playlistRepository = PlaylistRepositoryImpl(scope = ...)
                )
            ) { }
            PlaylistsScreen(
                ...
            )
        }

        composable(route = Routes.Search.route) {

            val searchViewModel: SearchViewModel = viewModel(
                factory = SearchViewModelFactory(
                    trackRepository = TrackRepositoryImpl(scope = ...)
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
