package ru.alexbur.smartwallet.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.alexbur.smartwallet.R

@Composable
fun <T> EditAndDeleteButton(modifier: Modifier, entity: T, edit: (T) -> Unit, delete: (T) -> Unit) {
    Row(modifier = modifier) {
        Image(
            modifier = Modifier
                .wrapContentWidth()
                .background(Color.Transparent)
                .padding(start = 16.dp)
                .clickable {
                    edit(entity)
                }.padding(8.dp),
            painter = painterResource(id = R.drawable.edit),
            contentDescription = "Edit"
        )
        Image(
            modifier = Modifier
                .wrapContentWidth()
                .background(Color.Transparent)
                .padding(horizontal = 16.dp)
                .clickable {
                    delete(entity)
                }.padding(8.dp),
            painter = painterResource(id = R.drawable.delete), contentDescription = "Delete"
        )
    }
}

@Preview
@Composable
fun EditPreview() {
    EditAndDeleteButton(modifier = Modifier.wrapContentWidth(), entity = 1, edit = {}, delete = {})
}