package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository

class PlaylistsViewModelFactory(
    private val playlistRepository: PlaylistRepository,
    private val trackRepository: TrackRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaylistsViewModel::class.java)) {
            return PlaylistsViewModel(playlistRepository, trackRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}