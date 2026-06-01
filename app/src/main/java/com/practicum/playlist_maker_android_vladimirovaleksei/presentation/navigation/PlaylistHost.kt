package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.playlist_maker_android_vladimirovaleksei.App
import com.practicum.playlist_maker_android_vladimirovaleksei.MainScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.data.PlaylistRepositoryImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.data.SearchHistoryRepositoryImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.TrackRepositoryImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.favorite.FavoriteViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.favorite.FavoriteViewModelFactory
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlist.PlaylistScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlist.PlaylistViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlist.PlaylistViewModelFactory
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists.NewPlaylistScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists.NewPlaylistViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists.NewPlaylistViewModelFactory
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists.PlaylistsScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.search.SearchScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.settings.SettingsScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists.PlaylistsViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists.PlaylistsViewModelFactory
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.search.SearchViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.search.SearchViewModelFactory
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.track.TrackScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.track.TrackViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.track.TrackViewModelFactory
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.favorite.FavoritesScreen

@Composable
fun PlaylistHost() {
    val navController = rememberNavController()
    val home = Routes.Main.route
    val app = LocalContext.current.applicationContext as App
    val database = app.database

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
            val favoriteViewModel: FavoriteViewModel = viewModel(
                factory = FavoriteViewModelFactory(
                    trackRepository = TrackRepositoryImpl(database = database)
                )
            )
            FavoritesScreen(
                modifier = Modifier,
                viewModel = favoriteViewModel,
                onBack = { navController.popBackStack() },
                onTrackClick = { trackId ->
                    navController.navigate(Routes.Track.trackRoute(trackId))
                }
            )
        }

        composable(route = Routes.Playlists.route) {
            val playlistsViewModel: PlaylistsViewModel = viewModel(
                factory = PlaylistsViewModelFactory(
                    playlistRepository = PlaylistRepositoryImpl(database = database),
                    trackRepository = TrackRepositoryImpl(database = database)
                )
            )
            PlaylistsScreen(
                modifier = Modifier,
                playlistsViewModel = playlistsViewModel,
                addNewPlaylist = { navController.navigate(Routes.NewPlaylist.route) },
                navigateToPlaylist = { playlistId ->
                    navController.navigate(Routes.Playlist.playlistRoute(playlistId))
                },
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(route = Routes.NewPlaylist.route) {
            val newPlaylistViewModel: NewPlaylistViewModel = viewModel(
                factory = NewPlaylistViewModelFactory(
                    playlistRepository = PlaylistRepositoryImpl(database = database)
                )
            )
            NewPlaylistScreen(
                viewModel = newPlaylistViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.Playlist.route,
            arguments = listOf(navArgument("playlistId") { type = NavType.LongType })
        ) { backStackEntry ->
            val playlistId = backStackEntry.arguments?.getLong("playlistId") ?: 0L
            val playlistViewModel: PlaylistViewModel = viewModel(
                factory = PlaylistViewModelFactory(
                    playlistId = playlistId,
                    playlistRepository = PlaylistRepositoryImpl(database = database),
                    trackRepository = TrackRepositoryImpl(database = database)
                )
            )
            PlaylistScreen(
                modifier = Modifier,
                playlistViewModel = playlistViewModel,
                index = playlistId.toInt(),
                onClick = { trackId ->
                    val resolvedId = trackId ?: return@PlaylistScreen
                    navController.navigate(Routes.Track.trackRoute(resolvedId.toLong()))
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.Track.route,
            arguments = listOf(navArgument("trackId") { type = NavType.LongType })
        ) { backStackEntry ->
            val trackId = backStackEntry.arguments?.getLong("trackId") ?: 0L
            val trackViewModel: TrackViewModel = viewModel(
                factory = TrackViewModelFactory(
                    trackId = trackId,
                    trackRepository = TrackRepositoryImpl(database = database),
                    playlistRepository = PlaylistRepositoryImpl(database = database)
                )
            )
            TrackScreen(
                modifier = Modifier,
                viewModel = trackViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(route = Routes.Search.route) {
            val searchViewModel: SearchViewModel = viewModel(
                factory = SearchViewModelFactory(
                    trackRepository = TrackRepositoryImpl(database = database),
                    searchHistoryRepository = SearchHistoryRepositoryImpl(
                        preferences = app.searchHistoryPreferences
                    )
                )
            )
            SearchScreen(
                modifier = Modifier,
                searchViewModel = searchViewModel,
                onClick = { navController.popBackStack() },
                onTrackClick = { track ->
                    searchViewModel.cacheTrack(track)
                    navController.navigate(Routes.Track.trackRoute(track.id))
                }
            )
        }

        composable(route = Routes.Settings.route) {
            SettingsScreen {
                navController.popBackStack()
            }
        }
    }
}
