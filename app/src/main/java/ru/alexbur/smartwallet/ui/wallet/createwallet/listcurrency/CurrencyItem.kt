package ru.alexbur.smartwallet.ui.wallet.createwallet.listcurrency

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.ui.utils.CustomRadioButton

@Composable
fun CurrencyItem(
    modifier: Modifier,
    currency: Currency,
    isSelect: Boolean,
    onClick: (Currency) -> Unit
) {
    Row(
        modifier = modifier.padding(vertical = 12.dp)
    ) {
        Text(
            text = stringResource(id = currency.nameListId), modifier = Modifier
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