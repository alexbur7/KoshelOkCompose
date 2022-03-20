package ru.alexbur.smartwallet.ui.listwallet.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.listwallet.CurrencyEntity
import ru.alexbur.smartwallet.domain.entities.listwallet.ExchangeRatesEntity
import ru.alexbur.smartwallet.domain.enums.Currency
import ru.alexbur.smartwallet.ui.theme.BackgroundCourseColor
import ru.alexbur.smartwallet.ui.theme.BackgroundMainCardFirstColor
import ru.alexbur.smartwallet.ui.theme.ShimmerPlaceHolderColor

@Composable
fun CurrenciesCard(
    modifier: Modifier,
    isShimmer: Boolean,
    exchangeRatesEntity: ExchangeRatesEntity
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(BackgroundCourseColor)
            .placeholder(
                visible = isShimmer,
                color = BackgroundMainCardFirstColor,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = ShimmerPlaceHolderColor
                )
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        CurrencyCard(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f),
            currencyEntity = exchangeRatesEntity.firstCurrency
        )

        CurrencyCard(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f),
            currencyEntity = exchangeRatesEntity.secondCurrency
        )

        CurrencyCard(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f),
            currencyEntity = exchangeRatesEntity.thirdCurrency
        )
    }
}

@Composable
fun CurrencyCard(
    modifier: Modifier,
    currencyEntity: CurrencyEntity
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                modifier = Modifier,
                text = currencyEntity.name.name,
                style = TextStyle(color = Color.White, fontSize = 13.sp)
            )

            Text(
                text = currencyEntity.course,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 4.dp),
                style = TextStyle(Color.White, fontSize = 13.sp)
            )

            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 6.dp),
                painter = painterResource(
                    id = if (currencyEntity.isUp) R.drawable.check_up else R.drawable.check_down
                ),
                contentDescription = "Up or down currency"
            )
        }
    }
}

@Preview
@Composable
fun CurrenciesCardPreview() {
    CurrenciesCard(
        modifier = Modifier.fillMaxWidth(),
        isShimmer = false, ExchangeRatesEntity(
            firstCurrency = CurrencyEntity(Currency.EUR, "35,1", false),
            secondCurrency = CurrencyEntity(Currency.USD, "54,1", true),
            thirdCurrency = CurrencyEntity(Currency.CHF, "98.1", false)
        )
    )
}