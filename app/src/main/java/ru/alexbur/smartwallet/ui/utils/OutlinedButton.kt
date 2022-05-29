package ru.alexbur.smartwallet.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.utils.theme.TextFieldBorderColor

@Composable
fun OutlinedButton(
    modifier: Modifier,
    textLabel: String,
    text: String,
    cornerRadius: Dp = 24.dp,
    onClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier
        .drawWithContent {
            drawRoundRect(
                color = TextFieldBorderColor,
                topLeft = Offset(0f, 10.dp.toPx()),
                cornerRadius = CornerRadius(cornerRadius.toPx()),
                style = Stroke(width = 1.dp.toPx())
            )
            drawContent()
        }
        .padding(horizontal = 8.dp)) {

        Text(
            text = textLabel,
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 8.dp)
                .background(BackgroundColor)
                .padding(horizontal = 4.dp),
            style = TextStyle(color = Color.White, fontSize = 12.sp)
        )

        TextWithEndImage(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    focusManager.clearFocus()
                    onClick()
                }
                .padding(12.dp),
            text = text,
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            imageId = R.drawable.arrow_right
        )
    }
}

@Preview
@Composable
fun OutlinedButtonPreview() {
    OutlinedButton(modifier = Modifier.fillMaxWidth(), textLabel = "Label", text = "Text") {

    }
}