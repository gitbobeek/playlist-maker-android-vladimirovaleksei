package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Playlist
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class PlaylistViewModel(
    playlistId: Long,
    playlistRepository: PlaylistRepository
) : ViewModel() {

    val playlist: StateFlow<Playlist?> = playlistRepository
        .getPlaylist(playlistId)
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
}

