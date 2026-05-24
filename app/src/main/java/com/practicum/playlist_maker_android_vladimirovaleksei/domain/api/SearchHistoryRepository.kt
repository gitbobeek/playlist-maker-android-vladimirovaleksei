package com.practicum.playlist_maker_android_vladimirovaleksei.domain.api

import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.Word

interface SearchHistoryRepository {
    suspend fun getHistoryRequests(): List<Word>

    fun addToHistory(word: Word)
}