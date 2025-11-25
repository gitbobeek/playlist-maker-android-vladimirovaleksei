package com.practicum.playlist_maker_android_vladimirovaleksei

sealed class Routes(val route: String) {
    object Main : Routes("main")
    object Favorite : Routes("favorite")
    object Library : Routes("library")
    object Search : Routes("search")
    object Settings : Routes("settings")
}