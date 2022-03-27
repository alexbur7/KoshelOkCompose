package ru.alexbur.smartwallet.ui.wallet.detailwallet.listtransactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundMainCardFirstColor
import ru.alexbur.smartwallet.ui.utils.theme.ShimmerPlaceHolderColor

@Composable
fun TransactionItem(
    modifier: Modifier,
    transaction: DetailWalletItem.Transaction,
    isShimmer: Boolean
) {
    Row(
        modifier = modifier
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
            contentDescription = "Transaction category icon"
        )

        Column(modifier = Modifier.fillMaxWidth().padding(start = 12.dp)) {
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
                    text = transaction.money + " " + transaction.currency.icon,
                    style = TextStyle(
                        color = if (transaction.category.isIncome) Color.Green else Color.Red,
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
                    style = TextStyle(Color.White, fontSize = 13.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f),
                    text = transaction.time,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 13.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}