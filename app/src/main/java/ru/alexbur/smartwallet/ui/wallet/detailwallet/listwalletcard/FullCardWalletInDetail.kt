package ru.alexbur.smartwallet.ui.wallet.detailwallet.listwalletcard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundMainCardFirstColor
import ru.alexbur.smartwallet.ui.utils.theme.ShimmerPlaceHolderColor
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FullCardWalletInDetail(
    pagerState: PagerState,
    wallets: List<WalletEntity>,
    isShimmer: Boolean
) {

    val isEmptyData = pagerState.currentPage >= 0 && pagerState.currentPage < wallets.size

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp)
                .placeholder(
                    visible = isShimmer,
                    color = BackgroundMainCardFirstColor,
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = ShimmerPlaceHolderColor
                    ), shape = RoundedCornerShape(12.dp)
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

                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                    }
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                wallet = if (isEmptyData) wallets[page] else null, isShimmer = isShimmer
            )
        }
    }
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}