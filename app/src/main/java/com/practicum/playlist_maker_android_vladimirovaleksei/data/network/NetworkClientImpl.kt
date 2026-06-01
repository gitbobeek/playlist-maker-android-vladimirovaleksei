package com.practicum.playlist_maker_android_vladimirovaleksei.data.network

import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.BaseResponse
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackSearchRequest
import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackSearchResponse
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.NetworkClient
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClientImpl : NetworkClient {

    private val itunesApi: ItunesApi = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ItunesApi::class.java)

    override fun doRequest(request: Any): BaseResponse {
        if (request is TrackSearchRequest) {
            return try {
                val response = itunesApi.searchTracks(request.expression).execute()
                Log.d("NetworkClient", "iTunes response code: ${response.code()}")
                val body = response.body() ?: TrackSearchResponse(0, emptyList())
                body.resultCode = response.code()
                body
            } catch (e: Exception) {
                Log.e("NetworkClient", "iTunes request failed", e)
                BaseResponse().apply { resultCode = -1 }
            }
        }

        return BaseResponse().apply { resultCode = 400 }
    }
}