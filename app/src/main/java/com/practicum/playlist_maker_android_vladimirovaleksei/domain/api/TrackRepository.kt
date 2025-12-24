package com.practicum.playlist_maker_android_vladimirovaleksei.domain.api

import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.Track

interface TrackRepository {
    suspend fun searchTracks(expression: String): List<Track>
}