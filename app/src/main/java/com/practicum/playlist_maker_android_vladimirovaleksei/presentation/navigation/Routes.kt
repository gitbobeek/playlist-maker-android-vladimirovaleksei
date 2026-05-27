package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.navigation

sealed class Routes(val route: String) {
    object Main : Routes("main")
    object Favorite : Routes("favorite")
    object Playlists : Routes("playlists")
    object NewPlaylist : Routes("new_playlist")
    object Search : Routes("search")
    object Settings : Routes("settings")
    object Playlist : Routes("playlist/{playlistId}")
    object Track : Routes("track/{trackId}")

    fun playlistRoute(playlistId: Long): String = "playlist/$playlistId"
    fun trackRoute(trackId: Long): String = "track/$trackId"
}