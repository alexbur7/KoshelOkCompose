package ru.alexbur.smartwallet.ui.wallet.detail.listtransactions

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundTransactionsColor
import ru.alexbur.smartwallet.ui.utils.theme.EmptyNotesTextColor

@Composable
fun TransactionsBottomSheet(
    modifier: Modifier,
    state: LazyListState,
    transactions: List<DetailWalletItem>,
    isShimmer: Boolean,
    textPaddingOffset: Float,
    editItem: (DetailWalletItem.Transaction) -> Unit,
    deleteItem: (Long) -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
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
                    drawRoundRect(
                        color = BackgroundColor,
                        cornerRadius = CornerRadius(2.dp.toPx())
                    )
                })
            }

            TransactionsList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                state = state,
                transactions = transactions,
                isShimmer = isShimmer,
                editItem = {
                    editItem(it)
                },
                deleteItem = {
                    deleteItem(it)
                }
            )
        }

        if (transactions.isEmpty()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = (textPaddingOffset * 300).dp),
                text = stringResource(id = R.string.empty_notes),
                style = TextStyle(
                    color = EmptyNotesTextColor,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}