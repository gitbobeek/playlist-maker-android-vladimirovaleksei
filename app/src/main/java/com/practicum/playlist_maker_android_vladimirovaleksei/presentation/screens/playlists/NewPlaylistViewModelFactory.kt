package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository

class NewPlaylistViewModelFactory(
    private val playlistRepository: PlaylistRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewPlaylistViewModel::class.java)) {
            return NewPlaylistViewModel(playlistRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

