package com.practicum.playlist_maker_android_vladimirovaleksei.domain.impl

import com.practicum.playlist_maker_android_vladimirovaleksei.domain.Track
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackSearchInteractor

class TrackSearchInteractorImpl(private val repository: TrackRepository) : TrackSearchInteractor {

    override fun searchTracks(expression: String): List<Track> {
        return repository.searchTracks(expression)
    }

    override fun loadTrackData(trackId: String, onComplete: () -> Unit) {
        TODO("Not yet implemented")
    }
}