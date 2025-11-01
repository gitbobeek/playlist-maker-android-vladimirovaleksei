package com.practicum.playlist_maker_android_vladimirovaleksei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.IconDarkGray
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.IconLightBlue
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.IconLightGray
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.IconSemitransparentBlue
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.yandexSansMedium
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.yandexSansRegular

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingsScreen()
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.settings),
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
    ) { paddingValues ->
        SettingsMenu(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        )
    }
}

@Composable
fun SettingsMenu(modifier: Modifier) {
    val sidesPaddingValue = 8.dp

    var isDarkThemeEnabled by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        SettingsMenuButtonWithThemeSwitch(
            titleId = R.string.dark_theme,
            isChecked = isDarkThemeEnabled,
            onCheckedChange = { isDarkThemeEnabled = it },
            sidesPaddingValue = sidesPaddingValue
        )

        SettingsMenuButton(R.string.share, R.drawable.ic_share, sidesPaddingValue)
        SettingsMenuButton(R.string.support, R.drawable.ic_support, sidesPaddingValue)
        SettingsMenuButton(R.string.user_agreement, R.drawable.ic_arrow_forward, sidesPaddingValue)
    }
}

/*
    TODO: Knob movement, Menu Button Click Effect
 */
@Composable
fun SettingsMenuButtonWithThemeSwitch(
    titleId: Int,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    sidesPaddingValue: Dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp)
            .padding(start = sidesPaddingValue * 2)
            .clickable { onCheckedChange(!isChecked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = titleId),
            fontFamily = yandexSansRegular,
            fontSize = 16.sp,
            color = Color.Black
        )

        ThemeSwitch(isChecked = isChecked, sidesPaddingValue)
    }
}


@Composable
fun ThemeSwitch(isChecked: Boolean, sidesPaddingValue: Dp) {
    val trackTint = if (isChecked) IconSemitransparentBlue else IconLightGray
    val knobTint = if (isChecked) IconLightBlue else IconDarkGray

    Box(
        modifier = Modifier
            .size(width = 56.dp, height = 40.dp)
            .padding(end = sidesPaddingValue),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.track ),
            contentDescription = null,
            tint = trackTint,
            modifier = Modifier.size(width = 32.dp, height = 16.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.knob),
            contentDescription = null,
            tint = knobTint,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(height = 18.dp, width = 18.dp)
        )
    }
}




@Composable
fun SettingsMenuButton(
    titleId: Int,
    iconId: Int,
    sidesPaddingValue: Dp
) {
    TextButton(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp)
            .padding(start = sidesPaddingValue),
        colors = ButtonDefaults.textButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(9f),
            textAlign = TextAlign.Start,
            text = stringResource(id = titleId),
            fontFamily = yandexSansRegular,
            fontSize = 16.sp,
        )

        Icon(
            modifier = Modifier
                .padding(end = sidesPaddingValue),
            painter = painterResource(iconId),
            contentDescription = stringResource(titleId),
            tint = IconDarkGray
        )
    }
}
