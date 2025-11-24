package com.practicum.playlist_maker_android_vladimirovaleksei

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.colors
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.BlueAccents
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.YpDarkGray
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.YpLightGray
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.yandexSansRegular

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchScreen()
        }
    }
}

@Preview(showSystemUi = true, device = "spec:width=411dp,height=891dp")
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    Scaffold(
        topBar = {
            SecondaryTopAppBar(R.string.search)
        }
    ) { paddingValues ->
        SearchField(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        )
    }
}

@Composable
fun SearchField(
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = query,
            onValueChange = {
                query = it
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
    }
}