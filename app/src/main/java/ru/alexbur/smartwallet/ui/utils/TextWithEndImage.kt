package ru.alexbur.smartwallet.ui.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

@Composable
fun TextWithEndImage(
    modifier: Modifier,
    text: String,
    textStyle: TextStyle,
    @DrawableRes imageId: Int? = null,
    contentDescription: String? = ""
) {
    Row(modifier = modifier) {
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            style = textStyle,
        )

        if (imageId != null) {
            Image(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = imageId),
                contentDescription = contentDescription
            )
        }
    }
}