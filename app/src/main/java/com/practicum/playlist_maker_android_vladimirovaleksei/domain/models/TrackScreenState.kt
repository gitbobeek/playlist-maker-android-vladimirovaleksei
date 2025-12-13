package com.practicum.playlist_maker_android_vladimirovaleksei.domain.models

sealed class TrackScreenState {

    object Loading: TrackScreenState()

    data class Content(
        val trackModel: TrackModel,
    ) : TrackScreenState()
}