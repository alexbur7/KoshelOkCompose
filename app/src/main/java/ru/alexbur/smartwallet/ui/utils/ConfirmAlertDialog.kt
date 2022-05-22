package ru.alexbur.smartwallet.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.ui.utils.theme.CheckedTrackColor
import ru.alexbur.smartwallet.ui.utils.theme.OutcomeColor
import ru.alexbur.smartwallet.ui.utils.theme.SecondCardWalletColor

@Composable
fun ConfirmAlertDialog(
    modifier: Modifier,
    text: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {},
        text = {
            Text(text = text, style = TextStyle(Color.White, fontSize = 20.sp))
        },
        backgroundColor = SecondCardWalletColor,
        confirmButton = {
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        onConfirm()
                    }
                    .background(Color.Transparent)
                    .padding(16.dp),
                text = stringResource(id = R.string.confrim),
                style = TextStyle(color = OutcomeColor, fontSize = 16.sp)
            )
        },
        dismissButton = {
            Text(
                modifier = Modifier
                    .wrapContentWidth()
                    .clickable {
                        onDismiss()
                    }
                    .background(Color.Transparent)
                    .padding(16.dp),
                text = stringResource(id = R.string.cancelable),
                style = TextStyle(color = CheckedTrackColor, fontSize = 16.sp)
            )
        }
    )
}

@Preview
@Composable
fun ConfirmDialogPreview() {
    var isShow by remember {
        mutableStateOf(true)
    }
    if (isShow) {
        ConfirmAlertDialog(
            modifier = Modifier.fillMaxWidth(),
            text = "Вы уверены что хотите удалить кошелек?",
            {
                isShow = false
            }
        ) {
            isShow = false
        }
    }
}