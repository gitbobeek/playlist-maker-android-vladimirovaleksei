package com.practicum.playlist_maker_android_vladimirovaleksei.data

import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.AppDatabase
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.entity.PlaylistEntity
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.toDomain
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.PlaylistRepository
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Playlist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PlaylistRepositoryImpl(
    private val database: AppDatabase
) : PlaylistRepository {

    private val playlistDao = database.playlistDao()

    override fun getPlaylist(playlistId: Long): Flow<Playlist?> {
        return playlistDao.getPlaylistWithTracks(playlistId).map { it?.toDomain() }
    }

    override fun getAllPlaylists(): Flow<List<Playlist>> {
        return playlistDao.getPlaylistsWithTracks().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun addNewPlaylist(name: String, description: String, coverImageUri: String?) {
        playlistDao.insertPlaylist(
            PlaylistEntity(
                name = name,
                description = description,
                coverImageUri = coverImageUri
            )
        )
    }

    override suspend fun deletePlaylistById(id: Long) {
        playlistDao.deletePlaylist(id)
    }
}
