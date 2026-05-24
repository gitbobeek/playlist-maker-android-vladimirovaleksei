package com.practicum.playlist_maker_android_vladimirovaleksei.data

import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.Word
import com.practicum.playlist_maker_android_vladimirovaleksei.data.preferences.SearchHistoryPreferences
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.SearchHistoryRepository

class SearchHistoryRepositoryImpl(
    private val preferences: SearchHistoryPreferences
) : SearchHistoryRepository {
    override suspend fun getHistoryRequests(): List<Word> {
        return preferences.getEntries().map { Word(it) }
    }

    override fun addToHistory(word: Word) {
        preferences.addEntry(word.word)
    }
}