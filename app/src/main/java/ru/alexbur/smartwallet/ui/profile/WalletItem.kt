package ru.alexbur.smartwallet.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.ui.utils.ProgressIndicator
import ru.alexbur.smartwallet.ui.utils.theme.*

@Composable
fun WalletItem(
    modifier: Modifier,
    walletEntity: WalletEntity,
    isShimmer: Boolean,
    onClick: (Long) -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                if (!isShimmer) {
                    onClick(walletEntity.id)
                }
            }
            .placeholder(
                visible = isShimmer,
                color = ThirdCardWalletColor,
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
                shape = RoundedCornerShape(12.dp)
            )
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        ThirdCardWalletColor,
                        SecondCardWalletColor,
                        SecondCardWalletColor,
                        FirstCardWalletColor
                    )
                )
            )
            .padding(horizontal = 21.dp)
    ) {

        NameAndAmountText(walletEntity = walletEntity)

        if (walletEntity.partSpending != null) {
            ProgressIndicator(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .height(6.dp), partSpending = walletEntity.partSpending
            )
        }
    }
}