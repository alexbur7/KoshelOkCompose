package ru.alexbur.smartwallet.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.alexbur.smartwallet.R

@Composable
fun ChooseDataToolbar(
    modifier: Modifier,
    close: () -> Unit,
    done: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.currency_close),
            contentDescription = stringResource(id = R.string.close_description),
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.TopStart)
                .clickable {
                    close()
                }
        )

        Image(
            painter = painterResource(id = R.drawable.currency_done),
            contentDescription = "Choose currency",
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .clip(RoundedCornerShape(8.dp))
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
    ChooseDataToolbar(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(), {}, {})
}