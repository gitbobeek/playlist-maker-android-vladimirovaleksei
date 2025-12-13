package com.practicum.playlist_maker_android_vladimirovaleksei

import android.app.Application
import com.practicum.playlist_maker_android_vladimirovaleksei.data.TrackRepositoryImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.NetworkClientImpl
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackRepository
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackSearchInteractor
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.impl.TrackSearchInteractorImpl

class MyApplication : Application() {

    private fun getTrackRepository(): TrackRepository {
        return TrackRepositoryImpl(NetworkClientImpl())
    }

    fun provideTrackSearchInteractor(): TrackSearchInteractor {
        return TrackSearchInteractorImpl(getTrackRepository())
    }
}