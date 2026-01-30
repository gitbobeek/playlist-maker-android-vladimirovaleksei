package com.practicum.playlist_maker_android_vladimirovaleksei.data

import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.DatabaseMock
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.Word
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.SearchHistoryRepository
import kotlinx.coroutines.CoroutineScope

class SearchHistoryRepositoryImpl(private val scope: CoroutineScope): SearchHistoryRepository {
    private val database = DatabaseMock(scope = scope)

    override fun getHistoryRequests(): List<Word> {
        return database.getHistory()
    }

    override fun addToHistory(word: Word) {
        database.addToHistory(word = word)
    }
}