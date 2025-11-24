package com.practicum.playlist_maker_android_vladimirovaleksei

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.yandexSansMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondaryTopAppBar(
    @StringRes titleId: Int
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black
        ),
        title = {
            Text(
                text = stringResource(id = titleId),
                fontFamily = yandexSansMedium
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(id = R.string.go_back)
                )
            }
        }
    )
}