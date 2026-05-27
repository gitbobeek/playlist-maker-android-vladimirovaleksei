package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Playlist
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Track
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TrackViewModel(
    trackId: Long,
    private val trackRepository: TrackRepository,
    playlistRepository: PlaylistRepository
) : ViewModel() {

    val track: StateFlow<Track?> = trackRepository
        .getTrackById(trackId)
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    val playlists: StateFlow<List<Playlist>> = playlistRepository
        .getAllPlaylists()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun toggleFavorite(isFavorite: Boolean) {
        val current = track.value ?: return
        viewModelScope.launch(Dispatchers.IO) {
            trackRepository.updateTrackFavoriteStatus(current, isFavorite)
        }
    }

    fun addToPlaylist(playlistId: Long) {
        val current = track.value ?: return
        viewModelScope.launch(Dispatchers.IO) {
            trackRepository.insertTrackToPlaylist(current, playlistId)
        }
    }
}

