package ru.alexbur.smartwallet.ui.wallet.createwallet.listcurrency

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import ru.alexbur.smartwallet.ui.utils.CustomRadioButton

@Composable
fun CurrencyItem(
    modifier: Modifier,
    currency: CurrencyEntity,
    isSelect: Boolean,
    onClick: (CurrencyEntity) -> Unit
) {
    Row(
        modifier = modifier.padding(vertical = 12.dp)
    ) {
        Text(
            text = currency.fullListName, modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.CenterVertically)
                .weight(8f),
            style = TextStyle(color = Color.White, fontSize = 16.sp)
        )

        CustomRadioButton(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp), isSelect = isSelect
        ) {
            onClick(currency)
        }
    }
}