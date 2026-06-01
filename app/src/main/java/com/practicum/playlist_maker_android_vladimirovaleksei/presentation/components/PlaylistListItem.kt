package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.practicum.playlist_maker_android_vladimirovaleksei.data.network.entity.Playlist

@Composable
fun PlaylistListItem(
    playlist: Playlist,
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = { onLongClick?.invoke() }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val placeholder = painterResource(R.drawable.ic_music)

        if (playlist.coverImageUri.isNullOrBlank()) {
            Image(
                modifier = Modifier.size(48.dp),
                painter = placeholder,
                contentDescription = stringResource(R.string.playlist_cover)
            )
        } else {
            AsyncImage(
                modifier = Modifier.size(48.dp),
                model = android.net.Uri.parse(playlist.coverImageUri),
                placeholder = placeholder,
                error = placeholder,
                contentDescription = stringResource(R.string.playlist_cover),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = playlist.name,
                fontSize = 16.sp
            )
            val text = "${playlist.tracks.size} tracks"
            Text(
                text = text,
                fontSize = 11.sp,
                color = Color.Gray
            )
        }
    }
}