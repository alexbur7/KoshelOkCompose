package ru.alexbur.smartwallet.ui.filter

import androidx.compose.foundation.Image
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import ru.alexbur.smartwallet.R

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
        placeholder = { Text(text = stringResource(id = R.string.name_place_holder)) },
    )
}