package ru.alexbur.smartwallet.ui.wallet.createwallet.listcurrency

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.ui.utils.theme.CheckedThumbColor
import ru.alexbur.smartwallet.ui.utils.theme.CheckedTrackColor
import ru.alexbur.smartwallet.ui.utils.theme.UnCheckedTrackColor

@Composable
fun CurrencyItem(
    currency: Currency,
    isCurrent: Boolean,
    onCheckedChange: (Currency) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = stringResource(id = currency.nameListId), modifier = Modifier
                .wrapContentHeight()
                .align(Alignment.CenterVertically)
                .weight(8f),
            style = TextStyle(color = Color.White, fontSize = 16.sp)
        )

        Switch(
            checked = isCurrent,
            onCheckedChange = {
                if (it) {
                    onCheckedChange(currency)
                }
            }, colors = SwitchDefaults.colors(
                checkedThumbColor = CheckedThumbColor,
                uncheckedThumbColor = UnCheckedTrackColor,
                checkedTrackColor = CheckedTrackColor,
                uncheckedTrackColor = UnCheckedTrackColor
            )
        )
    }
}

@Preview("Currency is current")
@Composable
fun CurrencyItemPreview() {
    CurrencyItem(currency = Currency.USD, isCurrent = true, onCheckedChange = {})
}

@Preview("Currency is not current")
@Composable
fun CurrencyItemPreviewTwo() {
    CurrencyItem(currency = Currency.CHF, isCurrent = false, onCheckedChange = {})
}