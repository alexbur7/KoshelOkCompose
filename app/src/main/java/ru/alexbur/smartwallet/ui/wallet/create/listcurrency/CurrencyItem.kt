package ru.alexbur.smartwallet.ui.wallet.create.listcurrency

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import ru.alexbur.smartwallet.ui.utils.TextWithEndImage

@Composable
fun CurrencyItem(
    modifier: Modifier,
    currency: CurrencyEntity,
    isSelect: Boolean,
    onClick: (CurrencyEntity) -> Unit
) {
    TextWithEndImage(
        modifier = modifier
            .clickable {
                onClick(currency)
            }
            .padding(16.dp),
        text = currency.fullListName,
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
        imageId = if (isSelect) R.drawable.choose_icon else null
    )
}

@Preview
@Composable
fun CurrencyPreview() {
    var isSelect by remember { mutableStateOf(false) }
    CurrencyItem(
        modifier = Modifier.fillMaxWidth(),
        currency = CurrencyEntity(
            id = 1,
            name = "gjnqenkjgn2",
            course = "fqkfmk",
            fullName = "f;kqwkf",
            fullListName = "eq;kkq2j3jpijt3ij",
            "%",
            isUp = false
        ),
        isSelect = isSelect,
        onClick = {
            isSelect = !isSelect
        })
}