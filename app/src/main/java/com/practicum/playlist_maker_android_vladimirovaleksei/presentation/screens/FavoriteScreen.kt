package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    favoriteViewModel: FavoriteViewModel,
    onBack: () -> Unit
) {
    val favorites by favoriteViewModel.favoriteTracks.collectAsState(emptyList())

    Scaffold(
        topBar = {
            SecondaryTopAppBar(
                titleId = R.string.favorite,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        if (favorites.isEmpty()) {
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
                items(favorites.size) { index ->
                    TrackListItem(
                        track = favorites[index],
                        onClick = { }
                    )
                }
            }
        }
    }
}