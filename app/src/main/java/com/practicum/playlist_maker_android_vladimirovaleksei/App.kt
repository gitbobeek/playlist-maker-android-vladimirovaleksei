package com.practicum.playlist_maker_android_vladimirovaleksei

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import android.util.Log
import java.security.Security
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
        try {
            // Disable CRL and OCSP revocation checks on Android to avoid SSL path validation
            // attempting to fetch revocation data over cleartext HTTP (crl.apple.com) which
            // may be blocked on device and cause SSLHandshakeException.
            Security.setProperty("com.sun.security.enableCRLDP", "false")
            Security.setProperty("ocsp.enable", "false")
        } catch (e: Exception) {
            Log.w("App", "Failed to disable CRL/OCSP checks", e)
        }
        database = Room.databaseBuilder(this, AppDatabase::class.java, "playlist_maker.db")
            .addMigrations(AppDatabase.MIGRATION_1_2)
            .build()
        searchHistoryPreferences = SearchHistoryPreferences(dataStore = searchHistoryDataStore)
    }
}
