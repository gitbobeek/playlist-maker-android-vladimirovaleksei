package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Track
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val trackRepository: TrackRepository
) : ViewModel() {
    val favoriteList: Flow<List<Track>> = trackRepository.getFavoriteTracks()

    fun toggleFavorite(track: Track, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            trackRepository.updateTrackFavoriteStatus(track, isFavorite)
        }
    }
}
