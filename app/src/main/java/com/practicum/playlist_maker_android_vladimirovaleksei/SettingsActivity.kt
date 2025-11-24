package com.practicum.playlist_maker_android_vladimirovaleksei

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.YpDarkGray
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.IconLightBlue
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.YpLightGray
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.IconSemitransparentBlue
import com.practicum.playlist_maker_android_vladimirovaleksei.ui.theme.yandexSansRegular
import androidx.core.net.toUri

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingsScreen()
        }
    }
}

@Preview
@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            SecondaryTopAppBar(R.string.settings)
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
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            context.startActivity(shareIntent)
        }
        SettingsMenuButton(R.string.support, R.drawable.ic_support) {

            val emailIntent: Intent = Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:".toUri()
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
