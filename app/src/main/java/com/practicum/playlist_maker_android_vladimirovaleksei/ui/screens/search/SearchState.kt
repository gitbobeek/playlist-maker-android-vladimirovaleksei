package com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.search

import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Track

sealed class SearchState {
    object Initial: SearchState()
    object Searching: SearchState()
    data class Success(val foundList: List<Track>): SearchState()
    data class Fail(val error: String): SearchState()
}