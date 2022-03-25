package ru.alexbur.smartwallet.ui.wallet.detailwallet.listwalletcard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundMainCardFirstColor
import ru.alexbur.smartwallet.ui.utils.theme.ShimmerPlaceHolderColor
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FullCardWalletInDetail(initialPage: Int, wallets: List<WalletEntity>, isShimmer: Boolean) {

    val pagerState = rememberPagerState(initialPage = if (initialPage >= 0 )initialPage else 0)
    val isEmptyData = initialPage >= 0 && initialPage < wallets.size

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 24.dp)
                .placeholder(
                    visible = isShimmer,
                    color = BackgroundMainCardFirstColor,
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = ShimmerPlaceHolderColor
                    )
                ),
            text = if (isEmptyData) wallets[pagerState.currentPage].name else "",
            style = TextStyle(color = Color.White, fontSize = 18.sp)
        )

        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            count = wallets.size,
            state = pagerState,
            itemSpacing = 16.dp,
            contentPadding = PaddingValues(horizontal = 50.dp)
        ) { page ->
            CardWalletInDetail(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
                    .fillMaxWidth().padding(top = 18.dp),
                wallet = if (isEmptyData)wallets[page] else null, isShimmer = isShimmer
            )
        }
    }
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
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