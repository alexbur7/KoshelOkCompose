package ru.alexbur.smartwallet.ui.wallet.detailwallet.listtransactions

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.dp
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundTransactionsColor

@Composable
fun TransactionsList(
    modifier: Modifier,
    state: LazyListState,
    transactions: List<DetailWalletItem>,
    isShimmer: Boolean
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(color = BackgroundTransactionsColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        ) {
            Canvas(modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.Center)
                .width(120.dp)
                .height(5.dp), onDraw = {
                drawRoundRect(color = BackgroundColor, cornerRadius = CornerRadius(2.dp.toPx()))
            })
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            state = state
        ) {

            items(
                transactions.size
            ) { index ->
                when (val item = transactions[index]) {
                    is DetailWalletItem.Transaction -> {
                        TransactionItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 24.dp)
                                .padding(top = 18.dp, bottom = if (index == transactions.lastIndex) 24.dp else 0.dp),
                            transaction = item,
                            isShimmer = isShimmer
                        )
                    }
                    is DetailWalletItem.Day -> {
                        DayItem(
                            Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(horizontal = 24.dp)
                                .padding(top = 24.dp, bottom = 6.dp),
                            item,
                            isShimmer
                        )
                    }
                }
            }
        }
    }
}