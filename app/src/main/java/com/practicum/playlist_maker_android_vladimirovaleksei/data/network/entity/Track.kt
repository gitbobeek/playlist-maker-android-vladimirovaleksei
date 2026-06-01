package com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity

data class Track(
    val id: Long,
    val trackName: String,
    val artistName: String,
    val trackTime: String,
    val image: String,
    var favorite: Boolean,
    var playlistId: Long
)