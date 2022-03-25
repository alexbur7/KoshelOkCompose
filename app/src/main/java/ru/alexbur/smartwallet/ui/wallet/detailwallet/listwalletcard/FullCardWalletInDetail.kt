package ru.alexbur.smartwallet.ui.wallet.detailwallet.listwalletcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.Currency

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FullCardWalletInDetail(initialPage: Int, wallets: List<WalletEntity>, isShimmer: Boolean) {

    val pagerState = rememberPagerState(initialPage = initialPage)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = wallets[pagerState.currentPage].name,
            style = TextStyle(color = Color.White, fontSize = 18.sp)
        )

        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            count = wallets.size,
            state = pagerState,
            itemSpacing = 32.dp
        ) {
            CardWalletInDetail(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 18.dp),
                wallet = wallets[pagerState.currentPage], isShimmer = isShimmer
            )
        }
    }
}

@Preview
@Composable
fun FullCardWalletInDetailPreview() {
    FullCardWalletInDetail(
        wallets = listOf(
            WalletEntity(
                id = 1,
                name = "Ejgkankj 24",
                amountMoney = "9631",
                incomeMoney = "5351",
                consumptionMoney = "12512525",
                limit = "2532",
                currency = Currency.RUB,
                isHide = false,
                partSpending = 0.52f
            ),
            WalletEntity(
                id = 31,
                name = "kgj 12452",
                amountMoney = "312421",
                incomeMoney = "125251",
                consumptionMoney = "122525",
                limit = "251232",
                currency = Currency.EUR,
                isHide = false,
                partSpending = 0.1f
            ),
            WalletEntity(
                id = 34,
                name = "12452",
                amountMoney = "312421",
                incomeMoney = "125251",
                consumptionMoney = "12512525",
                limit = "2532",
                currency = Currency.USD,
                partSpending = 0.35f,
                isHide = false
            )
        ), isShimmer = false, initialPage = 1
    )
}