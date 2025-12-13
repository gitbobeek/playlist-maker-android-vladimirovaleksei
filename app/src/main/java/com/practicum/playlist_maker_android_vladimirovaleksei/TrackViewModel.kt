package com.practicum.playlist_maker_android_vladimirovaleksei

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackPlayer
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.api.TrackSearchInteractor
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.models.PlayerStatus
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.models.TrackScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrackViewModel(
    private val trackId: String,
    private val tracksInteractor: TrackSearchInteractor,
    private val trackPlayer: TrackPlayer,
) : ViewModel() {

    private val _trackScreenState = MutableStateFlow(TrackScreenState.Loading)
    val trackScreenState = _trackScreenState.asStateFlow()
    private val _playerStatusState = MutableStateFlow(PlayerStatus.Initial)
    val playerStatusState = _playerStatusState.asStateFlow()

    init {
        tracksInteractor.loadTrackData(
            trackId = trackId,
            onComplete = { trackModel ->
                _trackScreenState.value = TrackScreenState.Content(trackModel)
            }
        )
    }

    fun play() {
        trackPlayer.play(
            trackId = trackId,
            // 1
            statusObserver = object : TrackPlayer.StatusObserver {
                override fun onProgress(progress: Float) {
                    // 2
                    _playerStatusState.value = getCurrentPlayStatus().copy(progress = progress)
                }

                override fun onStop() {
                    // 3
                    _playerStatusState.value = getCurrentPlayStatus().copy(isPlaying = false)
                }

                override fun onPlay() {
                    // 4
                    _playerStatusState.value = getCurrentPlayStatus().copy(isPlaying = true)
                }
            },
        )
    }

    // 5
    fun pause() {
        trackPlayer.pause(trackId)
    }

    // 6
    override fun onCleared() {
        trackPlayer.release(trackId)
    }


    companion object {
        fun getViewModelFactory(trackId: String): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myApp = this[APPLICATION_KEY] as MyApplication
                val interactor = myApp.provideTracksInteractor()
                val trackPlayer = myApp.provideTrackPlayer()

                TrackViewModel(
                    trackId,
                    interactor,
                    trackPlayer,
                )
            }
        }
    }
}