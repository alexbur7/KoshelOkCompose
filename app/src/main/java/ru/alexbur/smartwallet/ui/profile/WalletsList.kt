package ru.alexbur.smartwallet.ui.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity

@Composable
fun WalletsList(modifier: Modifier, state: LazyListState, wallets: List<WalletEntity>, clickItem: (Long)-> Unit) {
    LazyColumn(
        modifier = modifier,
        state = state
    ) {
        items(
            wallets.size
        ) { index ->
            WalletItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(
                        top = 12.dp,
                        bottom = if (wallets.lastIndex == index) 12.dp else 0.dp
                    ),
                wallets[index],
                isShimmer = wallets == MainScreenDataEntity.shimmerData.wallets
            ) {
                clickItem(it)
            }
        }
    }
}