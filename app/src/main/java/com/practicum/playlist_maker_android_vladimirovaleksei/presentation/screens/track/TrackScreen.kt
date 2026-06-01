package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.track

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.practicum.playlist_maker_android_vladimirovaleksei.R
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.PlaylistListItem
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.SecondaryTopAppBar
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.theme.yandexSansMedium
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.theme.yandexSansRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackScreen(
    modifier: Modifier = Modifier,
    viewModel: TrackViewModel,
    onBack: () -> Unit
) {
    val track by viewModel.track.collectAsState()
    val playlists by viewModel.playlists.collectAsState(emptyList())
    val placeholder = painterResource(id = R.drawable.ic_music)
    var isSheetVisible by rememberSaveable { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        topBar = {
            SecondaryTopAppBar(
                titleId = R.string.track,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        if (track == null) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(R.string.no_songs_found))
            }
        } else {
            val current = track!!
            val trackName = current.trackName.ifBlank { stringResource(R.string.unknown_track) }
            val artistName = current.artistName.ifBlank { stringResource(R.string.unknown_artist) }
            val trackTime = current.trackTime.ifBlank { "00:00" }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (current.image.isBlank()) {
                    Image(
                        modifier = Modifier.size(200.dp),
                        painter = placeholder,
                        contentDescription = trackName
                    )
                } else {
                    AsyncImage(
                        model = current.image,
                        contentDescription = trackName,
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop,
                        placeholder = placeholder,
                        error = placeholder
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = trackName,
                    fontFamily = yandexSansMedium,
                    fontSize = 20.sp
                )
                Text(
                    text = artistName,
                    fontFamily = yandexSansRegular,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Text(
                    text = trackTime,
                    fontFamily = yandexSansRegular,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val favoriteIcon = if (current.favorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
                    val favoriteDescription = if (current.favorite) {
                        stringResource(R.string.remove_from_favorites)
                    } else {
                        stringResource(R.string.add_to_favorites)
                    }

                    IconButton(onClick = { viewModel.toggleFavorite(!current.favorite) }) {
                        Icon(
                            imageVector = favoriteIcon,
                            contentDescription = favoriteDescription
                        )
                    }
                    IconButton(onClick = { isSheetVisible = true }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.PlaylistAdd,
                            contentDescription = stringResource(R.string.add_to_playlist)
                        )
                    }
                }
            }
        }
    }

    if (isSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isSheetVisible = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.add_to_playlist),
                    fontFamily = yandexSansMedium,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                if (playlists.isEmpty()) {
                    Text(text = stringResource(R.string.no_playlists))
                } else {
                    androidx.compose.foundation.lazy.LazyColumn {
                        items(playlists.size) { index ->
                            val playlist = playlists[index]
                            PlaylistListItem(
                                playlist = playlist,
                                onClick = {
                                    viewModel.addToPlaylist(playlist.id)
                                    isSheetVisible = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
