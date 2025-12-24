package com.practicum.playlist_maker_android_vladimirovaleksei.ui.screens.search

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicum.playlist_maker_android_vladimirovaleksei.R
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.components.SecondaryTopAppBar
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.components.TrackListItem
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.BlueAccents
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.YpDarkGray
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.YpLightGray
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.yandexSansRegular

@Composable
fun SearchScreen(
    onClick: () -> Unit,
    viewModel: SearchViewModel = viewModel(
        factory = SearchViewModel.getViewModelFactory()
    )
) {
    val screenState by viewModel.searchScreeState.collectAsState()

    var query by rememberSaveable { mutableStateOf("") }


    Scaffold(
        topBar = {
            SecondaryTopAppBar(R.string.search) { onClick() }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = query,
                onValueChange = {
                    query = it
                    viewModel.search(query)
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search),
                        fontFamily = yandexSansRegular,
                        fontSize = 16.sp
                    )
                },
                singleLine = true,

                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = stringResource(id = R.string.search),
                        modifier = Modifier.size(14.dp),
                        tint = YpDarkGray
                    )
                },
                trailingIcon = {
                    if (query != "")
                        IconButton(
                            onClick = {
                                query = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = YpDarkGray
                            )
                        }
                },

                shape = RoundedCornerShape(16.dp),
                colors = colors(
                    focusedContainerColor = YpLightGray,
                    unfocusedContainerColor = YpLightGray,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedPlaceholderColor = YpDarkGray,
                    disabledPlaceholderColor = YpDarkGray,
                    unfocusedPlaceholderColor = YpDarkGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    cursorColor = BlueAccents
                ),
            )

            when (screenState) {
                is SearchState.Initial -> {
                    Text(
                        text = stringResource(id = R.string.enter_the_search_string),
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
                    val tracks =
                        (screenState as SearchState.Success).foundList

                    LazyColumn {
                        items(tracks.size) { index ->
                            TrackListItem(tracks[index])
                        }
                    }
                }

                is SearchState.Fail -> {
                    val error =
                        (screenState as SearchState.Fail).error

                    Text(
                        text = "Ошибка: $error",
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}