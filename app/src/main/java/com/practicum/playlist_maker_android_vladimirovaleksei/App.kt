package com.practicum.playlist_maker_android_vladimirovaleksei

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.practicum.playlist_maker_android_vladimirovaleksei.data.db.AppDatabase
import com.practicum.playlist_maker_android_vladimirovaleksei.data.preferences.SearchHistoryPreferences

private val Context.searchHistoryDataStore by preferencesDataStore(name = "search_history")

class App : Application() {
    lateinit var database: AppDatabase
        private set

    lateinit var searchHistoryPreferences: SearchHistoryPreferences
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "playlist_maker.db").build()
        searchHistoryPreferences = SearchHistoryPreferences(dataStore = searchHistoryDataStore)
    }
}
