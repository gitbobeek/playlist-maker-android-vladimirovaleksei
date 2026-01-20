package com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.TrackRepositoryImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository

class SearchViewModelFactory(
    private val trackRepository: TrackRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(trackRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
