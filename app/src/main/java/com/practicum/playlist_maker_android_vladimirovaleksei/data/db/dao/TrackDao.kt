package com.practicum.playlist_maker_android_vladimirovaleksei.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.entity.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: TrackEntity)

    @Query("SELECT * FROM tracks WHERE trackName = :trackName AND artistName = :artistName LIMIT 1")
    fun getByNameAndArtist(trackName: String, artistName: String): Flow<TrackEntity?>

    @Query("SELECT * FROM tracks WHERE favorite = 1")
    fun getFavoriteTracks(): Flow<List<TrackEntity>>

    @Query("DELETE FROM tracks WHERE playlistId = :playlistId")
    suspend fun deleteTracksByPlaylistId(playlistId: Long)

    @Query("UPDATE tracks SET playlistId = 0 WHERE trackId = :trackId")
    suspend fun clearTrackFromPlaylist(trackId: Long)

    @Query("SELECT * FROM tracks WHERE trackId = :trackId LIMIT 1")
    fun getTrackById(trackId: Long): Flow<TrackEntity?>
}
