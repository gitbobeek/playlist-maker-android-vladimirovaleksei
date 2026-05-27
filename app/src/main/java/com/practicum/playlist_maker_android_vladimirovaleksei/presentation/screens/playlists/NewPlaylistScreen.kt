package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.playlists

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil3.compose.AsyncImage
import com.practicum.playlist_maker_android_vladimirovaleksei.R
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.SecondaryTopAppBar
import kotlinx.coroutines.launch

@Composable
fun NewPlaylistScreen(
    modifier: Modifier = Modifier,
    viewModel: NewPlaylistViewModel,
    onBack: () -> Unit
) {
    val coverImageUri by viewModel.coverImageUri.collectAsState()
    val placeholder = painterResource(id = R.drawable.ic_music)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var playlistName by rememberSaveable { mutableStateOf("") }
    var playlistDescription by rememberSaveable { mutableStateOf("") }

    val pickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        viewModel.setCoverImageUri(uri?.toString())
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            pickerLauncher.launch("image/*")
        } else {
            Toast.makeText(
                context,
                context.getString(R.string.permission_required),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    val openImagePicker = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pickerLauncher.launch("image/*")
        } else {
            val permissionGranted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == android.content.pm.PackageManager.PERMISSION_GRANTED

            if (permissionGranted) {
                pickerLauncher.launch("image/*")
            } else {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    Scaffold(
        topBar = {
            SecondaryTopAppBar(
                titleId = R.string.create_playlist,
                onClick = onBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .clickable(onClick = openImagePicker),
                contentAlignment = Alignment.Center
            ) {
                if (coverImageUri.isNullOrBlank()) {
                    Image(
                        painter = placeholder,
                        contentDescription = stringResource(R.string.playlist_cover),
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    AsyncImage(
                        model = android.net.Uri.parse(coverImageUri),
                        contentDescription = stringResource(R.string.playlist_cover),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        placeholder = placeholder,
                        error = placeholder
                    )
                }
            }

            Text(
                text = stringResource(R.string.select_image),
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = playlistName,
                onValueChange = { playlistName = it },
                label = { Text(text = stringResource(R.string.playlist_name)) },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = playlistDescription,
                onValueChange = { playlistDescription = it },
                label = { Text(text = stringResource(R.string.playlist_description)) },
                minLines = 3
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (playlistName.isBlank()) return@Button
                    scope.launch {
                        viewModel.createPlaylist(
                            name = playlistName.trim(),
                            description = playlistDescription.trim()
                        )
                        onBack()
                    }
                },
                enabled = playlistName.isNotBlank()
            ) {
                Text(text = stringResource(R.string.create))
            }
        }
    }
}

