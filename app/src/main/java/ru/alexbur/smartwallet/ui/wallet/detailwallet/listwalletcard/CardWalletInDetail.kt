package ru.alexbur.smartwallet.ui.wallet.detailwallet.listwalletcard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.ui.profile.toolbar.BalanceColumn
import ru.alexbur.smartwallet.ui.utils.ProgressIndicator
import ru.alexbur.smartwallet.ui.utils.theme.*


@Composable
fun CardWalletInDetail(
    modifier: Modifier,
    wallet: WalletEntity?,
    isShimmer: Boolean
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .placeholder(
                visible = isShimmer,
                color = BackgroundMainCardFirstColor,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = ShimmerPlaceHolderColor
                )
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    listOf(
                        CardFirstBorderColor,
                        PingDarkWithAlphaColor
                    )
                ),
                shape = RoundedCornerShape(24.dp)
            )
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        ThirdCardWalletColor,
                        SecondCardWalletColor
                    )
                )
            )
            .padding(24.dp)
    ) {

        if (wallet == null) return

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 6.dp),
            text = wallet.amountMoney + " " + wallet.currency.icon,
            style = TextStyle(color = Color.White, fontSize = 32.sp, fontWeight = FontWeight(500))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 24.dp)
        ) {
            BalanceColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                colorIndicator = Color.Green,
                text = stringResource(id = R.string.income_text),
                money = wallet.incomeMoney + " " + wallet.currency.icon
            )

            BalanceColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                colorIndicator = Color.Red,
                text = stringResource(id = R.string.consumption_text),
                money = wallet.consumptionMoney + " " + wallet.currency.icon
            )
        }

        if (wallet.partSpending != null) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                text = wallet.limit + " " + wallet.currency.icon,
                style = TextStyle(
                    color = TransparentWhite,
                    fontSize = 9.sp,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.End
                )
            )

            ProgressIndicator(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .fillMaxWidth()
                    .height(6.dp), wallet.partSpending
            )
        }
    }
}

@Preview
@Composable
fun CardWalletInDetailPreview() {
    CardWalletInDetail(
        modifier = Modifier.fillMaxWidth(),
        wallet = WalletEntity(
            id = 124,
            name = "12452",
            amountMoney = "312421",
            incomeMoney = "125251",
            consumptionMoney = "12512525",
            limit = "2532",
            currency = Currency.USD,
            isHide = false,
            partSpending = 0.5f
        ), isShimmer = false
    )
}

@Preview
@Composable
fun CardWalletInDetailWithoutLimitPreview() {
    CardWalletInDetail(
        modifier = Modifier.fillMaxWidth(),
        wallet = WalletEntity(
            id = 12,
            name = "12452",
            amountMoney = "312421",
            incomeMoney = "12551",
            consumptionMoney = "12525",
            limit = null,
            currency = Currency.USD,
            isHide = false,
            partSpending = null
        ), isShimmer = false
    )
}