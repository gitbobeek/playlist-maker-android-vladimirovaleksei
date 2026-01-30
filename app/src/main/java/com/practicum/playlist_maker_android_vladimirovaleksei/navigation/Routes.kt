package com.practicum.playlist_maker_android_vladimirovaleksei.navigation

sealed class Routes(val route: String) {
    object Main : Routes("main")
    object Favorite : Routes("favorite")
    object Playlists : Routes("playlists")
    object Search : Routes("search")
    object Settings : Routes("settings")
}