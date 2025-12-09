package com.practicum.playlist_maker_android_vladimirovaleksei

import androidx.activity.ComponentActivity
import androidx.activity.viewModels

class TrackActivity : ComponentActivity() {

    private val viewModel: TrackViewModel by viewModels<TrackViewModel> { TrackViewModel.getViewModelFactory(trackId = "123") }
}