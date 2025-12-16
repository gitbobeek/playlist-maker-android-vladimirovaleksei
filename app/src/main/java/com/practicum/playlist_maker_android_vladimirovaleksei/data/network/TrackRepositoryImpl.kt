package com.practicum.playlist_maker_android_vladimirovaleksei.data.network

import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.NetworkClient
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackSearchRequest
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackSearchResponse
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import kotlinx.coroutines.delay

class TrackRepositoryImpl(private val networkClient: NetworkClient) : TrackRepository {

    override suspend fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TrackSearchRequest(expression))

        delay(1000)

        return if (response.resultCode == 200) {
            (response as TrackSearchResponse).results.map {
                val seconds = it.trackTimeMillis / 1000
                val minutes = seconds / 60
                val trackTime = "%02d".format(minutes) + ":" + "%02d".format(seconds - minutes * 60)
                Track(it.trackName, it.artistName, trackTime)
            }
        } else {
            emptyList()
        }
    }
}