package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens

import androidx.lifecycle.ViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Track
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import kotlinx.coroutines.flow.Flow

class FavoriteViewModel(
    trackRepository: TrackRepository
) : ViewModel() {
    val favoriteTracks: Flow<List<Track>> = trackRepository.getFavoriteTracks()
}

