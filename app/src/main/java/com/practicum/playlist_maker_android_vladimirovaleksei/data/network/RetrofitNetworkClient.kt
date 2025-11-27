package com.practicum.playlist_maker_android_vladimirovaleksei.data.network

import com.practicum.playlist_maker_android_vladimirovaleksei.data.NetworkClient
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.BaseResponse
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackSearchResponse

class RetrofitNetworkClient : NetworkClient {
    override fun doRequest(dto: Any): BaseResponse {
        return TrackSearchResponse(listOf())
    }
}