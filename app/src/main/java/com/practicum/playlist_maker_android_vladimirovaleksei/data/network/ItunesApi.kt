package com.practicum.playlist_maker_android_vladimirovaleksei.data.network

import com.practicum.playlist_maker_android_vladimirovaleksei.data.dto.TrackSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {
    @GET("search")
    fun searchTracks(
        @Query("term") term: String,
        @Query("entity") entity: String = "song"
    ): Call<TrackSearchResponse>
}

