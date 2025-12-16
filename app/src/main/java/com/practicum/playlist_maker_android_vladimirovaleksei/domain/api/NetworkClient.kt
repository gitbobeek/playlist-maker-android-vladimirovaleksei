package com.practicum.playlist_maker_android_vladimirovaleksei.domain.api

import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(request: Any): BaseResponse
}