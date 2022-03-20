package ru.alexbur.smartwallet.ui.listcurrency

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ru.alexbur.smartwallet.R

@Composable
fun CurrencyToolbar(
    modifier: Modifier,
    close: () -> Unit,
    done: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.currency_close),
            contentDescription = "Currency close",
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .align(Alignment.TopStart)
                .clickable {
                    close()
                }
        )

        Image(
            painter = painterResource(id = R.drawable.currency_done),
            contentDescription = "Currency close",
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .align(Alignment.TopEnd)
                .clickable {
                    done()
                    close()
                }
        )
    }
}

@Preview
@Composable
fun CurrencyToolbarPreview() {
    CurrencyToolbar(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(), {}, {})
}