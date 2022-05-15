package ru.alexbur.smartwallet.ui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.ui.utils.theme.TextFieldBorderColor

@Composable
fun OutlinedEditText(
    modifier: Modifier,
    textLabel: String,
    initialField: String? = null,
    onValueChanged: (String) -> Unit = { },
    readOnly: Boolean = false,
    maxLength: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    var text by remember {
        mutableStateOf(initialField)
    }

    OutlinedTextField(
        modifier = modifier,
        value = text ?: "",
        onValueChange = {
            if ((it.isNumber() || keyboardOptions == KeyboardOptions.Default) && it.length < maxLength) {
                text = it
                onValueChanged(it)
            }
        },
        readOnly = readOnly,
        label = {
            Text(text = textLabel, style = TextStyle(color = Color.White, fontSize = 12.sp))
        },
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = TextFieldBorderColor,
            unfocusedBorderColor = TextFieldBorderColor,
            cursorColor = TextFieldBorderColor
        ),
        textStyle = TextStyle(fontSize = 16.sp, color = Color.White),
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon
    )
}

private val integerChars = '0'..'9'

private fun String.isNumber(): Boolean {
    var dotOccurred = 0
    if (firstOrNull() == '.' || firstOrNull() == '0') return false
    return all { it in integerChars || it == '.' && dotOccurred++ < 1 }
}

@Preview
@Composable
fun OutlinedEditTextPreview() {
    OutlinedEditText(
        modifier = Modifier.fillMaxWidth(),
        initialField = "",
        textLabel = "Название кошелька",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}