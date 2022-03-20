package ru.alexbur.smartwallet.ui.listwallet.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import ru.alexbur.smartwallet.ui.theme.BackgroundMainCardColor
import ru.alexbur.smartwallet.ui.theme.ShimmerPlaceHolderColor

@Composable
fun CardWithBalance(
    modifier: Modifier,
    balance: BalanceEntity,
    isShimmer: Boolean
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(color = BackgroundMainCardColor)
            .placeholder(
                visible = isShimmer,
                color = BackgroundMainCardColor,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = ShimmerPlaceHolderColor
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
            text = stringResource(id = R.string.count_money,balance.amountMoney),
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
                money = balance.incomeMoney
            )

            BalanceColumn(
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f),
                colorIndicator = Color.Red,
                text = stringResource(id = R.string.general_consumption),
                money = balance.consumptionMoney
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
        balance = BalanceEntity("10000000000000000000", "10253950385298532985913593523", "913523355313"),
        isShimmer = false
    )
}