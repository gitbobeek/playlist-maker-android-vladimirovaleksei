package com.practicum.playlist_maker_android_vladimirovaleksei

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import coil3.compose.AsyncImage
import com.practicum.playlist_maker_android_vladimirovaleksei.domain.models.TrackScreenState
import kotlinx.coroutines.launch

class TrackActivity : ComponentActivity() {
    private val viewModel by viewModels<TrackViewModel> { TrackViewModel.getViewModelFactory("123") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TrackScreen(viewModel)
        }
    }
}

@Composable
fun TrackScreen(viewModel: TrackViewModel) {
    //1
    val screenState by viewModel.trackScreenState.collectAsState()

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        when (screenState) { // 2
            is TrackScreenState.Content -> {
                Column {
                    AsyncImage(
                        model = screenState.trackModel.pictureUrl,
                        contentDescription = null
                    )
                    Text(screenState.trackModel.author)
                    Text(screenState.trackModel.name)
                }
            }

            is TrackScreenState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun TrackScreen(viewModel: TrackViewModel) {
    val screenState by viewModel.trackScreenState.collectAsState()

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        when (screenState) {
            is TrackScreenState.Content -> {
                TrackScreenContent(viewModel, screenState)
            }

            is TrackScreenState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TrackScreenContent(viewModel: TrackViewModel, screenState: TrackScreenState) {
    val playerStatus by viewModel.playerStatusState.collectAsState() //2

    Column {
        AsyncImage(
            model = screenState.trackModel.pictureUrl,
            contentDescription = null
        )
        Text(screenState.trackModel.author)
        Text(screenState.trackModel.name)

        val buttonIcon = if (playerStatus.isPlaying) ... else ...
        Button(content = { Image(buttonIcon) }, onClick = { ... })

        Slider(value = playStatus.progress) //5
    }
}
