package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists

import androidx.lifecycle.ViewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class NewPlaylistViewModel(
    private val playlistRepository: PlaylistRepository
) : ViewModel() {

    private val _coverImageUri = MutableStateFlow<String?>(null)
    val coverImageUri: StateFlow<String?> = _coverImageUri.asStateFlow()

    fun setCoverImageUri(uri: String?) {
        _coverImageUri.value = uri
    }

    suspend fun createPlaylist(name: String, description: String) {
        withContext(Dispatchers.IO) {
            playlistRepository.addNewPlaylist(
                name = name,
                description = description,
                coverImageUri = _coverImageUri.value
            )
        }
    }
}

