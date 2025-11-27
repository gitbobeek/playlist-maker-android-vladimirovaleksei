package com.practicum.playlist_maker_android_vladimirovaleksei.domain.api

import com.practicum.playlist_maker_android_vladimirovaleksei.domain.Track

interface TrackRepository {
    fun searchTracks(expression: String): List<Track>
}