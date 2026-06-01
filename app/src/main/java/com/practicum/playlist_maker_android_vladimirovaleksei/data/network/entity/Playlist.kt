package com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity

data class Playlist(
    val id: Long = 0,
    val name: String,
    val description: String,
    val coverImageUri: String? = null,
    val tracks: List<Track>
)