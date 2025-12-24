package com.practicum.playlist_maker_android_vladimirovaleksei.data.network

import com.practicum.playlist_maker_android_vladimirovaleksei.creator.Storage
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.NetworkClient
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.BaseResponse
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackSearchRequest
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackSearchResponse

class NetworkClientImpl(private val storage: Storage) : NetworkClient {

    override fun doRequest(request: Any): BaseResponse {
        val searchList = storage.search((request as TrackSearchRequest).expression)
        return TrackSearchResponse(searchList).apply { resultCode = 200 }
    }
}