package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository

class PlaylistViewModelFactory(
    private val playlistId: Long,
    private val playlistRepository: PlaylistRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaylistViewModel::class.java)) {
            return PlaylistViewModel(playlistId, playlistRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

