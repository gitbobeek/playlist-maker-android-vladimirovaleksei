package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.practicum.playlist_maker_android_vladimirovaleksei.R
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Track

@Composable
fun TrackListItem(
    track: Track,
    onLongClick: (() -> Unit)? = null,
    onClick: () -> Unit
) {
    val trackName = track.trackName.ifBlank { stringResource(R.string.unknown_track) }
    val artistName = track.artistName.ifBlank { stringResource(R.string.unknown_artist) }
    val trackTime = track.trackTime.ifBlank { "00:00" }
    val placeholder = painterResource(id = R.drawable.ic_music)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 32.dp)
            .combinedClickable(
                onClick = { onClick() },
                onLongClick = { onLongClick?.invoke() }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (track.image.isBlank()) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = placeholder,
                contentDescription = "Трек $trackName",
            )
        } else {
            AsyncImage(
                modifier = Modifier.size(64.dp),
                model = track.image,
                placeholder = placeholder,
                error = placeholder,
                contentDescription = "Трек $trackName",
            )
        }
        Column(
            modifier = Modifier.weight(1f).padding(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(trackName, fontWeight = FontWeight.Bold)
            Text(artistName)
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(trackTime)
        }
    }
}