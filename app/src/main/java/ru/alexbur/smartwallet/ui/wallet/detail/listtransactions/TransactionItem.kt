package ru.alexbur.smartwallet.ui.wallet.detail.listtransactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.data.extentions.formattedMoney
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.ui.utils.EditAndDeleteButton
import ru.alexbur.smartwallet.ui.utils.theme.*
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TransactionItem(
    modifier: Modifier,
    transaction: DetailWalletItem.Transaction,
    isShimmer: Boolean,
    onEdit: (DetailWalletItem.Transaction) -> Unit,
    onDelete: (Long) -> Unit
) {
    val swipeState = rememberSwipeableState(0)
    val pixel = with(LocalDensity.current) { -110.dp.toPx() }
    val anchors = mapOf(0f to 0, pixel to 1)
    Box(
        modifier = modifier
            .swipeable(
                state = swipeState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
    ) {
        Row(
            modifier = Modifier
                .offset { IntOffset(swipeState.offset.value.roundToInt(), 0) }
                .fillMaxWidth()
                .wrapContentHeight()
                .placeholder(
                    visible = isShimmer,
                    color = BackgroundMainCardFirstColor,
                    highlight = PlaceholderHighlight.shimmer(
                        highlightColor = ShimmerPlaceHolderColor
                    ), shape = RoundedCornerShape(12.dp)
                )
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(BackgroundColor),
                painter = painterResource(id = transaction.category.iconId),
                contentDescription = stringResource(id = R.string.category_image_description),
                contentScale = ContentScale.None
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(1f),
                        text = transaction.category.operation,
                        style = TextStyle(Color.White, fontSize = 16.sp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(1f),
                        text = transaction.money.formattedMoney(
                            transaction.currency.icon,
                            if (transaction.category.isIncome) "+" else "-"
                        ),
                        style = TextStyle(
                            color = if (transaction.category.isIncome) IncomeColor else OutcomeColor,
                            fontSize = 16.sp
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(1f),
                        text = stringResource(id = transaction.category.type.textId),
                        style = TextStyle(TransparentWhite, fontSize = 13.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(1f),
                        text = transaction.time,
                        style = TextStyle(
                            color = TransparentWhite,
                            fontSize = 13.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End
                    )
                }
            }
        }

        if (swipeState.offset.value.roundToInt() < -300) {
            EditAndDeleteButton(modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterEnd),
                entity = transaction,
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