package ru.alexbur.smartwallet.ui.transactions.create

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.enums.TypeOperation
import ru.alexbur.smartwallet.ui.utils.TextWithEndImage
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.utils.theme.TextFieldBorderColor

@Composable
fun TypeOperationChooser(
    modifier: Modifier,
    textLabel: String,
    currentOperationType: TypeOperation,
    changeTypeOperation: (TypeOperation) -> Unit
) {

    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier
        .drawWithContent {
            drawRoundRect(
                color = TextFieldBorderColor,
                topLeft = Offset(0f, 10.dp.toPx()),
                cornerRadius = CornerRadius(24.dp.toPx()),
                style = Stroke(width = 1.dp.toPx())
            )
            drawContent()
        }.padding(horizontal = 8.dp)) {

        Text(
            text = textLabel,
            modifier = Modifier
                .wrapContentWidth()
                .padding(start = 8.dp)
                .background(BackgroundColor)
                .padding(horizontal = 4.dp),
            style = TextStyle(color = Color.White, fontSize = 12.sp)
        )

        TextWithEndImage(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                focusManager.clearFocus()
                isExpanded = !isExpanded
            }
            .padding(12.dp),
            text = stringResource(id = currentOperationType.typeId),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            imageId = R.drawable.arrow_right
        )

        AnimatedVisibility(visible = isExpanded, modifier = Modifier.fillMaxWidth().padding(
            horizontal = 12.dp
        )) {
            Column {

                TextWithEndImage(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        focusManager.clearFocus()
                        changeTypeOperation(TypeOperation.SELECT_INCOME)
                        isExpanded = !isExpanded
                    }
                    .padding(vertical = 12.dp),
                    text = stringResource(id = TypeOperation.SELECT_INCOME.typeId),
                    textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                    imageId = if (currentOperationType == TypeOperation.SELECT_INCOME) R.drawable.choose_icon else null)

                TextWithEndImage(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        changeTypeOperation(TypeOperation.SELECT_EXPENSE)
                        isExpanded = !isExpanded
                    }
                    .padding(vertical = 12.dp),
                    text = stringResource(id = TypeOperation.SELECT_EXPENSE.typeId),
                    textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
                    imageId = if (currentOperationType == TypeOperation.SELECT_EXPENSE) R.drawable.choose_icon else null)
            }
        }
    }
}

@Preview
@Composable
fun TypeOperationSelectorIsExpanded() {
    var currentOperationType by remember{
        mutableStateOf(TypeOperation.SELECT_INCOME)
    }
    TypeOperationChooser(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        textLabel = "Тип",
        currentOperationType = currentOperationType
    ){
        currentOperationType = it
    }
}