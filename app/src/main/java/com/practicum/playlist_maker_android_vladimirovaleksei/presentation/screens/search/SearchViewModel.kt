package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.Word
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Track
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.SearchHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException

@OptIn(FlowPreview::class)
class SearchViewModel(
    private val trackRepository: TrackRepository,
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    private val _searchScreenState = MutableStateFlow<SearchState>(SearchState.Initial)
    val searchScreenState = _searchScreenState.asStateFlow()

    private val _history = MutableStateFlow<List<Word>>(emptyList())
    val history = _history.asStateFlow()

    private var lastFailedQuery: String? = null


    init {
        viewModelScope.launch {
            _history.value = searchHistoryRepository.getHistoryRequests()
        }

        viewModelScope.launch {
            _searchQuery
                .debounce(1000)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isNotEmpty()) {
                        performSearch(query)
                    }
                }
        }
    }


    fun updateQuery(query: String) {
        _searchQuery.value = query
    }

    private fun performSearch(request: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _searchScreenState.update { SearchState.Searching }

                searchHistoryRepository.addToHistory(Word(word = request))
                _history.update {
                    searchHistoryRepository.getHistoryRequests()
                }

                val list = trackRepository.searchTracks(request)
                lastFailedQuery = null
                _searchScreenState.update {
                    SearchState.Success(list)
                }
            } catch (e: IOException) {
                lastFailedQuery = request
                _searchScreenState.update {
                    SearchState.Fail(e.message ?: "Unknown error")
                }
            }
        }
    }

    fun retryLastSearch() {
        val query = lastFailedQuery ?: return
        if (query.isBlank()) return
        performSearch(query)
    }

    fun clearSearch() {
        _searchScreenState.update { SearchState.Initial }
    }

    fun getHistoryList(): List<Word> = history.value

    fun cacheTrack(track: Track) {
        viewModelScope.launch(Dispatchers.IO) {
            trackRepository.saveTrack(track)
        }
    }
}