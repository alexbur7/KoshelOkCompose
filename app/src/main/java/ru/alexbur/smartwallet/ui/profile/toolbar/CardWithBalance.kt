package ru.alexbur.smartwallet.ui.profile.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.listwallet.BalanceEntity
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundMainCardFirstColor
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundMainCardSecondColor
import ru.alexbur.smartwallet.ui.utils.theme.CardFirstBorderColor
import ru.alexbur.smartwallet.ui.utils.theme.ShimmerPlaceHolderColor

@Composable
fun CardWithBalance(
    modifier: Modifier,
    balance: BalanceEntity,
    isShimmer: Boolean
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .placeholder(
                visible = isShimmer,
                color = BackgroundMainCardFirstColor,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = ShimmerPlaceHolderColor
                )
            )
            .border(
                width = 1.dp, brush = Brush.linearGradient(
                    listOf(
                        CardFirstBorderColor,
                        Color.Transparent,
                        Color.Transparent
                    )
                ), shape = RoundedCornerShape(24.dp)
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        BackgroundMainCardFirstColor,
                        BackgroundMainCardSecondColor
                    )
                )
            )
            .padding(24.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.general_score),
            style = TextStyle(color = Color.White, fontSize = 13.sp)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 6.dp),
            text = stringResource(id = R.string.count_money, balance.amountMoney),
            style = TextStyle(color = Color.White, fontSize = 32.sp, fontWeight = FontWeight(500))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 90.dp)
        ) {
            BalanceColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                colorIndicator = Color.Green,
                text = stringResource(id = R.string.general_income),
                money = stringResource(id = R.string.count_money, balance.incomeMoney)
            )

            BalanceColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                colorIndicator = Color.Red,
                text = stringResource(id = R.string.general_consumption),
                money = stringResource(id = R.string.count_money, balance.consumptionMoney)
            )
        }
    }
}

@Preview
@Composable
fun CardWithBalancePreview() {
    CardWithBalance(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        balance = BalanceEntity(
            "10000000000000000000",
            "10253950385298532985913593523",
            "913523355313"
        ),
        isShimmer = false
    )
}