package com.practicum.playlist_maker_android_vladimirovaleksei.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.dao.PlaylistDao
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.dao.TrackDao
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.entity.PlaylistEntity
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.entity.TrackEntity

@Database(
    entities = [PlaylistEntity::class, TrackEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
    abstract fun trackDao(): TrackDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE playlists ADD COLUMN coverImageUri TEXT")
            }
        }
    }
}
