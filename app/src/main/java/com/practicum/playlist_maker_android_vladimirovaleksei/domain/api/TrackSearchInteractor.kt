package com.practicum.playlist_maker_android_vladimirovaleksei.domain.api

import com.practicum.playlist_maker_android_vladimirovaleksei.domain.Track

interface TrackSearchInteractor {

    fun searchTracks(expression: String): List<Track>

    fun loadTrackData(trackId: String, onComplete: () -> Unit)
}