package com.practicum.playlist_maker_android_vladimirovaleksei

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackSearchInteractor

class TrackViewModel(
    private val trackId: String,
    private val trackSearchInteractor: TrackSearchInteractor
) : ViewModel() {

    init {
        Log.d("test", "init: $trackId")
    }

    companion object {
        fun getViewModelFactory(trackId: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TrackViewModel(
                        trackId,
                        Creator.provideTrackSearchInteractor()
                    ) as T
                }
            }
    }
}