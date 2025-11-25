package com.practicum.playlist_maker_android_vladimirovaleksei

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.playlist_maker_android_vladimirovaleksei.composables.horizontalPopEnterTransition
import com.practicum.playlist_maker_android_vladimirovaleksei.composables.horizontalPopExitTransition
import com.practicum.playlist_maker_android_vladimirovaleksei.composables.horizontalSlideEnterTransition
import com.practicum.playlist_maker_android_vladimirovaleksei.composables.horizontalSlideExitTransition
import com.practicum.playlist_maker_android_vladimirovaleksei.screens.FavoriteScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.screens.LibraryScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.screens.SearchScreen
import com.practicum.playlist_maker_android_vladimirovaleksei.screens.SettingsScreen

@Composable
fun PlaylistHost() {
    val navController = rememberNavController()
    val home = Routes.Main.route

    NavHost(navController = navController, startDestination = home) {
        animatedComposable(
            route = home,
            exitTransition = { horizontalSlideExitTransition() }
        ) {
            MainScreen(
                onSearch = { navController.navigate(Routes.Search.route) },
                onPlaylists = { navController.navigate(Routes.Library.route) },
                onFavorite = { navController.navigate(Routes.Favorite.route) },
                onSettings = { navController.navigate(Routes.Settings.route) },
            )
        }

        animatedComposable(Routes.Favorite.route) {
            FavoriteScreen()
        }

        animatedComposable(Routes.Library.route) {
            LibraryScreen()
        }

        animatedComposable(Routes.Search.route) {
            SearchScreen {
                navController.popBackStack()
            }
        }

        animatedComposable(Routes.Settings.route) {
            SettingsScreen {
                navController.popBackStack()
            }
        }
    }
}

private fun NavGraphBuilder.animatedComposable(
    route: String,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition)? = { horizontalSlideEnterTransition() },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition)? = { horizontalSlideExitTransition() },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition)? = { horizontalPopEnterTransition() },
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition)? = { horizontalPopExitTransition() },
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content
    )
}
