package ru.alexbur.smartwallet.ui.utils

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.alexbur.smartwallet.ui.utils.theme.ExceedLimitColor
import ru.alexbur.smartwallet.ui.utils.theme.ProgressBarColor

@Composable
fun ProgressIndicator(modifier: Modifier, partSpending: Float) {
    Canvas(modifier = modifier, onDraw = {
        drawRoundRect(
            color = Color.White.copy(alpha = 0.7f),
            cornerRadius = CornerRadius(8.dp.toPx())
        )
        drawRoundRect(
            color = if (partSpending >= 1) ExceedLimitColor else ProgressBarColor,
            cornerRadius = CornerRadius(8.dp.toPx()),
            size = Size(this.size.width * partSpending, this.size.height)
        )
    })
}