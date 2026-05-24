package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository

class FavoriteViewModelFactory(
    private val trackRepository: TrackRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(trackRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

