package ru.alexbur.smartwallet.ui.listwallet.toolbar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BalanceColumn(
    modifier: Modifier,
    colorIndicator: Color,
    text: String,
    money: String
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier) {
            Canvas(modifier = Modifier
                .size(8.dp)
                .align(Alignment.CenterVertically), onDraw = {
                drawCircle(color = colorIndicator)
            })
            Text(
                modifier = Modifier.padding(start = 6.dp),
                text = text,
                style = TextStyle(color = Color.White, fontSize = 13.sp)
            )
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = money,
            style = TextStyle(color = Color.White, fontSize = 16.sp, fontWeight = FontWeight(500))
        )
    }
}
