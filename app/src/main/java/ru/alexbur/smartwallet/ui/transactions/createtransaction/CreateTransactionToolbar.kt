package ru.alexbur.smartwallet.ui.transactions.createtransaction

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.R

@Composable
fun CreateTransactionToolbar(
    modifier: Modifier,
    close: () -> Unit
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.close_screen),
            contentDescription = stringResource(
                id = R.string.close_description
            ),
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.CenterVertically)
                .clickable {
                    close()
                }
        )

        Text(
            text = stringResource(id = R.string.operation_title),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 24.dp),
            style = TextStyle(Color.White, fontSize = 20.sp, fontWeight = FontWeight(500))
        )
    }
}