package ru.alexbur.smartwallet.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GradientButton(
    buttonState: ButtonState,
    text: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    progressBarSettings: ProgressBarSettings = ProgressBarSettings(),
    onClick: () -> Unit = { },
) {
    Box(
        modifier = modifier
    ) {
        when (buttonState) {
            ButtonState.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(13.dp)
                        .align(Alignment.Center)
                        .size(progressBarSettings.size),
                    strokeWidth = progressBarSettings.strokeWidth,
                    color = progressBarSettings.color
                )
            }
            ButtonState.ENABLED -> {
                Text(text = text, modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick()
                    }
                    .padding(14.dp),
                    style = textStyle
                )
            }
            ButtonState.DISABLED -> {
                Text(
                    text = text, modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    style = textStyle
                )
            }
        }
    }
}

data class ProgressBarSettings(
    val color: Color = Color.White,
    val strokeWidth: Dp = 4.dp,
    val size: Dp = 24.dp
)

enum class ButtonState {
    LOADING, ENABLED, DISABLED
}