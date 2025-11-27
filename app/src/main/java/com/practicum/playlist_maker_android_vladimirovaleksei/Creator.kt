package com.practicum.playlist_maker_android_vladimirovaleksei

import com.practicum.playlist_maker_android_vladimirovaleksei.data.TrackRepositoryImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.RetrofitNetworkClient
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackSearchInteractor
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.impl.TrackSearchInteractorImpl

object Creator {

    private fun getTrackRepository(): TrackRepository {
        return TrackRepositoryImpl(RetrofitNetworkClient())
    }

    fun provideTrackSearchInteractor(): TrackSearchInteractor {
        return TrackSearchInteractorImpl(getTrackRepository())
    }
}