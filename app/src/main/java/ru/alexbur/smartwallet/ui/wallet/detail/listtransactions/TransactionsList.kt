package ru.alexbur.smartwallet.ui.wallet.detail.listtransactions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem

@Composable
fun TransactionsList(
    modifier: Modifier,
    state: LazyListState,
    transactions: List<DetailWalletItem>,
    isShimmer: Boolean,
    editItem: (DetailWalletItem.Transaction) -> Unit,
    deleteItem: (Long) -> Unit
) {
    LazyColumn(
        modifier = modifier,
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
                            .padding(
                                top = 18.dp,
                                bottom = if (index == transactions.lastIndex) 24.dp else 0.dp
                            ),
                        transaction = item,
                        isShimmer = isShimmer,
                        onEdit = { editItem(it) },
                        onDelete = { deleteItem(it) }
                    )
                }
                is DetailWalletItem.Day -> {
                    DayItem(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 24.dp, bottom = 6.dp),
                        item,
                        isShimmer
                    )
                }
            }
        }
    }
}