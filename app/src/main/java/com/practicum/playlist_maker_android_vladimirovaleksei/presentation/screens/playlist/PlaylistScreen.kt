package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_vladimirovaleksei.R
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.TrackListItem
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.theme.yandexSansMedium
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.theme.yandexSansRegular
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistScreen(
    modifier: Modifier = Modifier,
    playlistViewModel: PlaylistViewModel,
    index: Int,
    onClick: (Int?) -> Unit,
    onBack: () -> Unit
) {
    val playlist by playlistViewModel.playlist.collectAsState(null)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text(
                        text = playlist?.name.orEmpty(),
                        fontFamily = yandexSansMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = stringResource(id = R.string.go_back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (playlist == null) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            val tracks = playlist?.tracks.orEmpty()
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(128.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.ic_music),
                    contentDescription = playlist?.name
                )
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = playlist?.name.orEmpty(),
                    fontFamily = yandexSansMedium,
                    fontSize = 20.sp
                )
                if (!playlist?.description.isNullOrBlank()) {
                    Text(
                        modifier = Modifier.padding(top = 6.dp),
                        text = playlist?.description.orEmpty(),
                        fontFamily = yandexSansRegular,
                        fontSize = 14.sp
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 6.dp, bottom = 8.dp),
                    text = "${tracks.size} tracks",
                    fontFamily = yandexSansRegular,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                if (tracks.isEmpty()) {
                    Text(
                        text = stringResource(R.string.no_songs_found),
                        modifier = Modifier.padding(top = 16.dp)
                    )
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(tracks.size) { trackIndex ->
                            val track = tracks[trackIndex]
                            TrackListItem(
                                track = track,
                                onClick = {
                                    val trackId = track.id.takeIf { it > 0 }?.toInt()
                                    onClick(trackId)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
