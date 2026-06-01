package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.practicum.playlist_maker_android_vladimirovaleksei.R
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.PlaylistListItem
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.SecondaryTopAppBar
import kotlinx.coroutines.launch

@Composable
fun PlaylistsScreen(
    modifier: Modifier,
    playlistsViewModel: PlaylistsViewModel,
    addNewPlaylist: () -> Unit,
    navigateToPlaylist: (Long) -> Unit,
    navigateBack: () -> Unit
) {
    val playlists by playlistsViewModel.playlists.collectAsState(emptyList())
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SecondaryTopAppBar(
                titleId = R.string.playlists,
                onClick = navigateBack
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 8.dp, end = 8.dp)
                ) {
                    items(playlists) { playlist ->
                        PlaylistListItem(
                            playlist = playlist,
                            onClick = { navigateToPlaylist(playlist.id) },
                            onLongClick = {
                                scope.launch {
                                    playlistsViewModel.deletePlaylistById(playlist.id)
                                }
                            }
                        )
                        HorizontalDivider(thickness = 0.5.dp)
                    }
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(32.dp)
                    .align(Alignment.BottomEnd),
                onClick = addNewPlaylist,
                containerColor = Color.Gray,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.add_playlist)
                )
            }
        }
    }
}