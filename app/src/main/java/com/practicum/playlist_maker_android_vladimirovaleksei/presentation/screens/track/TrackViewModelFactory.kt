package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository

class TrackViewModelFactory(
    private val trackId: Long,
    private val trackRepository: TrackRepository,
    private val playlistRepository: PlaylistRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrackViewModel::class.java)) {
            return TrackViewModel(trackId, trackRepository, playlistRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

