package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_vladimirovaleksei.R
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.SecondaryTopAppBar
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.TrackListItem
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.theme.BlueAccents
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.theme.YpDarkGray
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.theme.YpLightGray
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.theme.yandexSansRegular

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel,
    onClick: () -> Unit
) {
    val screenState by searchViewModel.searchScreenState.collectAsState()
    val historyList by searchViewModel.history.collectAsState()

    var query by rememberSaveable { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(query) {
        searchViewModel.updateQuery(query)
    }

    LaunchedEffect(screenState) {
        if (screenState is SearchState.Success) {
            focusManager.clearFocus()
        }
    }

    Scaffold(
        topBar = {
            SecondaryTopAppBar(
                titleId = R.string.search,
                onClick = onClick
            )
        }
    ) { paddingValues ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .onFocusChanged { isFocused = it.isFocused },

                value = query,
                onValueChange = { query = it },

                placeholder = {
                    Text(
                        text = stringResource(R.string.search),
                        fontFamily = yandexSansRegular,
                        fontSize = 16.sp
                    )
                },

                singleLine = true,

                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = stringResource(R.string.search),
                        modifier = Modifier.size(14.dp),
                        tint = YpDarkGray
                    )
                },

                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                query = ""
                                searchViewModel.clearSearch()
                                focusManager.clearFocus()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(R.string.clear_search),
                                tint = YpDarkGray
                            )
                        }
                    }
                },

                shape = if (isFocused && query.isEmpty() && historyList.isNotEmpty())
                    RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 8.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                else
                    RoundedCornerShape(8.dp),

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = YpLightGray,
                    unfocusedContainerColor = YpLightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedPlaceholderColor = YpDarkGray,
                    unfocusedPlaceholderColor = YpDarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = BlueAccents
                )
            )

            if (isFocused && query.isEmpty() && historyList.isNotEmpty()) {
                HistoryRequests(
                    historyList = historyList,
                    onClick = { word ->
                        query = word.word
                    }
                )
            }

            when (screenState) {

                is SearchState.Initial -> {
                    Text(
                        text = stringResource(R.string.enter_the_search_string),
                        modifier = Modifier.padding(16.dp)
                    )
                }

                is SearchState.Searching -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is SearchState.Success -> {
                    val tracks = (screenState as SearchState.Success).foundList

                    if (tracks.isEmpty()) {
                        SearchPlaceholder(
                            imageRes = R.drawable.ic_music,
                            textRes = R.string.no_songs_found
                        )
                    } else {
                        LazyColumn {
                            items(tracks.size) { index ->
                                TrackListItem(
                                    track = tracks[index],
                                    onClick = {}
                                )
                            }
                        }
                    }
                }

                is SearchState.Fail -> {
                    SearchPlaceholder(
                        imageRes = R.drawable.ic_music,
                        textRes = R.string.error
                    )
                    Button(onClick = { searchViewModel.retryLastSearch() }) {
                        Text(text = stringResource(R.string.refresh))
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchPlaceholder(
    imageRes: Int,
    textRes: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = stringResource(id = textRes),
            modifier = Modifier.size(96.dp)
        )
        Text(
            text = stringResource(id = textRes),
            modifier = Modifier.padding(top = 12.dp)
        )
    }
}
