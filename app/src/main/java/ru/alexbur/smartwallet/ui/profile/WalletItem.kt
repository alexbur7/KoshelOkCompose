package ru.alexbur.smartwallet.ui.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.ui.utils.theme.*

@Composable
fun WalletItem(
    walletEntity: WalletEntity,
    isShimmer: Boolean,
    isLast: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 12.dp, bottom = if (isLast) 12.dp else 0.dp)
            .clip(RoundedCornerShape(12.dp))
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
            Canvas(modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .height(6.dp), onDraw = {
                drawRoundRect(
                    color = Color.White.copy(alpha = 0.7f),
                    cornerRadius = CornerRadius(8.dp.value)
                )
                drawRoundRect(
                    color = ProgressBarColor,
                    cornerRadius = CornerRadius(8.dp.value),
                    size = Size(this.size.width * walletEntity.partSpending, this.size.height)
                )
            })
        }
    }
}

@Composable
private fun NameAndAmountText(
    walletEntity: WalletEntity
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 19.dp,
                bottom = if (walletEntity.partSpending == null) 19.dp else 5.dp
            )
    ) {
        Text(
            text = walletEntity.name, modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            style = TextStyle(color = Color.White, fontSize = 16.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = walletEntity.amountMoney + " " + walletEntity.currency.icon,
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            style = TextStyle(color = Color.White, fontSize = 16.sp),
            textAlign = TextAlign.End,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun WalletItemWithLimitPreview() {
    WalletItem(
        WalletEntity(
            1,
            "кошелек2333551355135531535",
            "1243132353532532553515",
            Currency.RUB,
            false,
            "100000",
            0.7f
        ), false,
        true
    )
}

@Preview
@Composable
fun WalletItemWithoutLimitPreview() {
    WalletItem(WalletEntity(1, "кошелек 22", "12431", Currency.RUB, false, null, null), true,true)
}