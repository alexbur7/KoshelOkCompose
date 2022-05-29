package ru.alexbur.smartwallet.ui.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.data.extentions.formattedMoney
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity

@Composable
fun NameAndAmountText(
    modifier: Modifier,
    walletEntity: WalletEntity
) {
    Row(
        modifier = modifier
            .padding(bottom = if (walletEntity.partSpending == null) 19.dp else 5.dp)
    ) {
        Text(
            text = walletEntity.name, modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            style = TextStyle(color = Color.White, fontSize = 16.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = walletEntity.amountMoney.formattedMoney(walletEntity.currency.icon),
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            style = TextStyle(color = Color.White, fontSize = 16.sp),
            textAlign = TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}