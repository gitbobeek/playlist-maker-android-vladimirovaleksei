package com.practicum.playlist_maker_android_vladimirovaleksei

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.ArrowForwardGray
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.MainBlue
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.yandexSansFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(activity = this@MainActivity)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(activity: Activity) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MainBlue,
                    titleContentColor = Color.White
                ),
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 14.dp),
                        text = stringResource(id = R.string.main_appname),
                        fontFamily = yandexSansFamily
                    )
                },
                expandedHeight = 76.dp
            )
        }
    ) { paddingValues ->
        NavigationMenu(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.White)
                .fillMaxSize(),
            activity = activity
        )
    }
}

@Composable
fun NavigationMenu(
    modifier: Modifier,
    activity: Activity
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        NavigationMenuButton(R.drawable.ic_search, R.string.search) {
            val intent = Intent(activity, SearchActivity::class.java)
            activity.startActivity(intent)
        }
        NavigationMenuButton(R.drawable.ic_library, R.string.playlists) {
            val intent = Intent(activity, LibraryActivity::class.java)
            activity.startActivity(intent)
        }
        NavigationMenuButton(R.drawable.ic_favorite_border, R.string.favorite) {
            val intent = Intent(activity, FavoriteActivity::class.java)
            activity.startActivity(intent)
        }
        NavigationMenuButton(R.drawable.ic_settings, R.string.settings) {
            val intent = Intent(activity, SettingsActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
@Composable
fun NavigationMenuButton(
    iconId: Int,
    titleId: Int,
    onClick: () -> Unit
) {

    TextButton(
        onClick = onClick,
        modifier = Modifier
            .padding(14.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(contentColor = Color.Black)
    ) {
        Icon(
            modifier = Modifier
                .padding(end = 10.dp)
                .align(Alignment.CenterVertically),
            painter = painterResource(id = iconId),
            contentDescription = stringResource(id = titleId)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(9f),
            text = stringResource(id = titleId),
            fontFamily = yandexSansFamily,
            fontSize = 22.sp
            )
        Icon(
            tint = ArrowForwardGray,
            painter = painterResource(id = R.drawable.ic_arrow_forward),
            contentDescription = stringResource(id = R.string.arrow_forward)
        )
    }
}