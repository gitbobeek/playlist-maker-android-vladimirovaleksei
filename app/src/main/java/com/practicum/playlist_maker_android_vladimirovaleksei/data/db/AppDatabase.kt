package com.practicum.playlist_maker_android_vladimirovaleksei.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.dao.PlaylistDao
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.dao.TrackDao
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.entity.PlaylistEntity
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.entity.TrackEntity

@Database(
    entities = [PlaylistEntity::class, TrackEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
    abstract fun trackDao(): TrackDao
}
