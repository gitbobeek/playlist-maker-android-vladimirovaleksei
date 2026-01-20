package com.practicum.playlist_maker_android_vladimirovaleksei.data.network

import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.BaseResponse
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.NetworkClient

class NetworkClientImpl() : NetworkClient {

    override fun doRequest(request: Any): BaseResponse {
        return BaseResponse()
    }
}