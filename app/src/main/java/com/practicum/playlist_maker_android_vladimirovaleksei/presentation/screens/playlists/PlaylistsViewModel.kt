package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Playlist
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Track
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class PlaylistsViewModel(
    private val playlistRepository: PlaylistRepository,
    private val trackRepository: TrackRepository
) : ViewModel() {

    val playlists: Flow<List<Playlist>> = playlistRepository.getAllPlaylists()

    val favoriteList: Flow<List<Track>> = trackRepository.getFavoriteTracks()

    fun createNewPlaylist(namePlaylist: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            playlistRepository.addNewPlaylist(namePlaylist, description)
        }
    }

    suspend fun insertTrackToPlaylist(track: Track, playlistId: Long) {
        trackRepository.insertTrackToPlaylist(track, playlistId)
    }

    suspend fun toggleFavorite(track: Track, isFavorite: Boolean) {
        trackRepository.updateTrackFavoriteStatus(track, isFavorite)
    }

    suspend fun deleteTrackFromPlaylist(track: Track) {
        trackRepository.deleteTrackFromPlaylist(track)
    }

    suspend fun deletePlaylistById(id: Long) {
        trackRepository.deleteTracksByPlaylistId(id)
        playlistRepository.deletePlaylistById(id)
    }

    suspend fun isExist(track: Track): Track? {
        return trackRepository.getTrackByNameAndArtist(track = track).firstOrNull()
    }
}