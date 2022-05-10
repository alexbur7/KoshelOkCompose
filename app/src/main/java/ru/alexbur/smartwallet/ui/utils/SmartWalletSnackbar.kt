package ru.alexbur.smartwallet.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.ui.utils.theme.SelectedRadioButtonColor

@Composable
fun SmartWalletSnackBar(snackbarData: SnackbarData) {
    Text(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(SelectedRadioButtonColor)
            .padding(16.dp),
        text = snackbarData.message,
        style = TextStyle(color = Color.White, fontSize = 16.sp, textAlign = TextAlign.Start)
    )
}

@Composable
@Preview
fun PreviewSnackBar() {
    SmartWalletSnackBar(snackbarData = object : SnackbarData {
        override val actionLabel: String?
            get() = null
        override val duration: SnackbarDuration
            get() = SnackbarDuration.Short
        override val message: String
            get() = "Ошибка сервера"

        override fun dismiss() {

        }

        override fun performAction() {

        }
    })
}