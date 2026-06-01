package com.practicum.playlist_maker_android_vladimirovaleksei.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.entity.PlaylistEntity
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.entity.PlaylistWithTracks
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaylistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: PlaylistEntity): Long

    @Transaction
    @Query("SELECT * FROM playlists")
    fun getPlaylistsWithTracks(): Flow<List<PlaylistWithTracks>>

    @Transaction
    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    fun getPlaylistWithTracks(playlistId: Long): Flow<PlaylistWithTracks?>

    @Query("DELETE FROM playlists WHERE id = :id")
    suspend fun deletePlaylist(id: Long)
}

