package ru.alexbur.smartwallet.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircleProgressBar(
    modifier: Modifier,
    isVisible: Boolean,
    progressBarSettings: ProgressBarSettings = ProgressBarSettings()
) {
    if (isVisible) {
        CircularProgressIndicator(
            modifier = modifier
                .padding(13.dp)
                .size(progressBarSettings.size),
            strokeWidth = progressBarSettings.strokeWidth,
            color = progressBarSettings.color
        )
    }
}