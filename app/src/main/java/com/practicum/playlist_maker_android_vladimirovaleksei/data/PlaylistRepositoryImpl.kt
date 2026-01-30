package com.practicum.playlist_maker_android_vladimirovaleksei.data

import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.DatabaseMock
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Playlist

class PlaylistRepositoryImpl() : PlaylistRepository {

    private val database = DatabaseMock(
        scope = scope
    )

    override fun getPlaylist(playlistId: Long): Flow<Playlist?> {
        return database.getPlaylist(playlistId)
    }

    override fun getAllPlaylists(): Flow<List<Playlist>> {
        return database.getAllPlaylists()
    }

    override suspend fun addNewPlaylist(name: String, description: String) {
        database.addNewPlaylist(
            name = name,
            description = description
        )
    }

    override suspend fun deletePlaylistById(id: Long) {
        database.deletePlaylistById(playlistId = id)
    }
}
