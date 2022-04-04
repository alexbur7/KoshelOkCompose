package ru.alexbur.smartwallet.ui.transactions.createtransaction

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.utils.theme.TextFieldBorderColor

@Composable
fun TypeOperationSelector(
    modifier: Modifier,
    textLabel: String,
    initialValue: String,
    isInitialExpanded: Boolean = false
) {

    var isExpanded by rememberSaveable {
        mutableStateOf(isInitialExpanded)
    }
    Column(modifier = modifier
        .drawWithContent {
            drawRoundRect(
                color = TextFieldBorderColor,
                topLeft = Offset(0f, 10.dp.toPx()),
                cornerRadius = CornerRadius(24.dp.toPx()),
                style = Stroke(width = 1.dp.toPx())
            )
            drawContent()
        }
        .padding(horizontal = 12.dp)) {

        Text(
            text = textLabel,
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 8.dp)
                .background(BackgroundColor)
                .padding(horizontal = 4.dp),
            style = TextStyle(color = Color.White, fontSize = 12.sp)
        )

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 4.dp)
            .clickable {
                isExpanded = !isExpanded
            }
            .padding(vertical = 12.dp)) {
            Text(
                text = initialValue,
                modifier = Modifier.weight(1f),
                style = TextStyle(color = Color.White, fontSize = 16.sp),
            )

            Image(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                painter = painterResource(id = R.drawable.arrow_right), contentDescription = ""
            )
        }

        AnimatedVisibility(visible = isExpanded, modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = initialValue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .clickable {

                        },
                    style = TextStyle(color = Color.White, fontSize = 16.sp),
                )

                Text(
                    text = initialValue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 12.dp)
                        .clickable {

                        },
                    style = TextStyle(color = Color.White, fontSize = 16.sp)
                )
            }
        }
    }
}

@Preview
@Composable
fun TypeOperationSelectorIsExpanded() {
    TypeOperationSelector(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        textLabel = "Тип",
        initialValue = "Расход"
    )
}