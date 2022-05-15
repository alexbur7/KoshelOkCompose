package ru.alexbur.smartwallet.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.ui.utils.EditAndDeleteButton
import ru.alexbur.smartwallet.ui.utils.ProgressIndicator
import ru.alexbur.smartwallet.ui.utils.theme.*
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WalletItem(
    modifier: Modifier,
    walletEntity: WalletEntity,
    isShimmer: Boolean,
    onClick: (Long) -> Unit,
    onEdit: (WalletEntity) -> Unit,
    onDelete: (Long) -> Unit
) {
    val swipeState = rememberSwipeableState(0)
    val anchors = mapOf(0f to 0, -400f to 1)
    Box(
        modifier = modifier
            .swipeable(
                state = swipeState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
    ) {
        Column(
            modifier = Modifier
                .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }
                .fillMaxWidth()
                .wrapContentWidth()
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

        if (swipeState.offset.value.roundToInt() < -300) {
            EditAndDeleteButton(modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterEnd),
                entity = walletEntity,
                edit = {
                    onEdit(it)
                },
                delete = {
                    onDelete(it.id)
                }
            )
        }
    }
}

@Preview
@Composable
fun WalletItem(){
    WalletItem(
        modifier = Modifier.fillMaxWidth(),
        walletEntity = WalletEntity.shimmerData.first(),
        isShimmer = false,
        onClick = {},
        onEdit = {},
        onDelete = {}
    )
}