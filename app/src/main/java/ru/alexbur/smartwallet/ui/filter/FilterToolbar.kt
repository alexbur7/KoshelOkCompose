package ru.alexbur.smartwallet.ui.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundCourseColor

@Composable
fun FilterToolbar(
    modifier: Modifier,
    filter: (String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        modifier = modifier,
        value = text,
        maxLines = 1,
        onValueChange = {
            text = it
            filter(it.text)
        },
        trailingIcon = {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = stringResource(id = R.string.find_description)
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.name_place_holder),
                style = TextStyle(color = Color.White.copy(alpha = 0.6f), fontSize = 16.sp)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            unfocusedIndicatorColor = BackgroundCourseColor,
            cursorColor = Color.White
        )
    )
}