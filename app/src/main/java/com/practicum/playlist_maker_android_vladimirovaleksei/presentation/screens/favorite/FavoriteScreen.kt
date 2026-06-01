package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.practicum.playlist_maker_android_vladimirovaleksei.R
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.SecondaryTopAppBar
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.TrackListItem

@Composable
fun FavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel,
    onBack: () -> Unit,
    onTrackClick: (Long) -> Unit
) {
    val favoriteList by viewModel.favoriteList.collectAsState(emptyList())

    Scaffold(
        topBar = {
            SecondaryTopAppBar(
                titleId = R.string.favorite,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        if (favoriteList.isEmpty()) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.no_songs_found))
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(favoriteList.size) { index ->
                    val track = favoriteList[index]
                    TrackListItem(
                        track = track,
                        onClick = { onTrackClick(track.id) },
                        onLongClick = { viewModel.toggleFavorite(track, false) }
                    )
                    HorizontalDivider(thickness = 0.5.dp)
                }
            }
        }
    }
}