package ru.alexbur.smartwallet.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.ui.utils.theme.TextGrayColor

@Composable
fun WelcomePartScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),

            painter = painterResource(id = R.drawable.auth_main),
            contentDescription = "Auth main image",
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = stringResource(id = R.string.title_welcome),
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight(700),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.text_welcome),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            color = TextGrayColor,
            style = MaterialTheme.typography.subtitle1,
            textAlign = TextAlign.Center
        )
    }
}