package com.practicum.playlist_maker_android_vladimirovaleksei.creator

import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.NetworkClientImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.TrackRepositoryImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository

object Creator {
    fun getTracksRepository(): TrackRepository {
        return TrackRepositoryImpl(NetworkClientImpl(Storage()))
    }
}