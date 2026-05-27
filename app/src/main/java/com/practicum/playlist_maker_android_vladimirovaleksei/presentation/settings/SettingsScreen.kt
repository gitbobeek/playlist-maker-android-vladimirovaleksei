package com.practicum.playlist_maker_android_vladimirovaleksei.presentation.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.practicum.playlist_maker_android_vladimirovaleksei.R
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.components.SecondaryTopAppBar
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.theme.YpDarkGray
import com.practicum.playlist_maker_android_vladimirovaleksei.presentation.theme.yandexSansRegular

@Composable
fun SettingsScreen(onClick: () -> Unit) {
    Scaffold(
        topBar = {
            SecondaryTopAppBar(R.string.settings, onClick)
        }
    ) { paddingValues ->
        SettingsMenu(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(vertical = paddingValues.calculateTopPadding() + 16.dp)
        )
    }
}

@Composable
fun SettingsMenu(modifier: Modifier) {

    val context = LocalContext.current

    val message = "This is my message to test message intent"

    val mailAddress = stringResource(R.string.mail_address)
    val mailSubject = stringResource(R.string.mail_subject)
    val mailBody = stringResource(R.string.mail_body)

    val userAgreementUrl = stringResource(id = R.string.user_agreement_url)

    Column(modifier = modifier) {

        SettingsMenuButton(R.string.share, R.drawable.ic_share) {
            val sendIntent: Intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, message)
                setType("text/plain")
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }
        SettingsMenuButton(R.string.support, R.drawable.ic_support) {

            val emailIntent: Intent = Intent(Intent.ACTION_SENDTO).apply {
                setData("mailto:".toUri())
                putExtra(Intent.EXTRA_EMAIL, mailAddress)
                putExtra(Intent.EXTRA_SUBJECT, mailSubject)
                putExtra(Intent.EXTRA_TEXT, mailBody)
            }

            try {
                context.startActivity(Intent.createChooser(emailIntent, "Choose Email Client..."))
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
        SettingsMenuButton(R.string.user_agreement, R.drawable.ic_arrow_forward) {
            val userAgreementIntent = Intent(Intent.ACTION_VIEW, userAgreementUrl.toUri())
            try {
                context.startActivity(userAgreementIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun SettingsMenuButton(
    titleId: Int,
    iconId: Int,
    onClick: () -> Unit
) {
    val sidesPaddingValue = 8.dp

    TextButton(
        onClick = onClick,
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
            tint = YpDarkGray
        )
    }
}
