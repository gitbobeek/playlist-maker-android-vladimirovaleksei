package com.practicum.playlist_maker_android_vladimirovaleksei.data

import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(dto: Any): BaseResponse
}