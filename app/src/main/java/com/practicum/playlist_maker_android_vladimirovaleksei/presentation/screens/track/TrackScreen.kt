package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.track

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.practicum.playlist_maker_android_vladimirovaleksei.R
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.SecondaryTopAppBar

@Composable
fun TrackScreen(
    modifier: Modifier = Modifier,
    trackId: Long,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            SecondaryTopAppBar(
                titleId = R.string.track,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.track) + ": " + trackId,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

