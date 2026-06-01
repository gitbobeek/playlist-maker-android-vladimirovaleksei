package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Playlist
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlaylistViewModel(
    playlistId: Long,
    private val playlistRepository: PlaylistRepository,
    private val trackRepository: TrackRepository
) : ViewModel() {

    val playlist: StateFlow<Playlist?> = playlistRepository
        .getPlaylist(playlistId)
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    fun deletePlaylist() {
        val playlistId = playlist.value?.id ?: return
        viewModelScope.launch(Dispatchers.IO) {
            trackRepository.deleteTracksByPlaylistId(playlistId)
            playlistRepository.deletePlaylistById(playlistId)
        }
    }
}
