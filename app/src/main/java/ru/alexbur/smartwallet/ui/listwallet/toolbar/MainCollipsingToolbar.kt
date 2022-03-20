package ru.alexbur.smartwallet.ui.listwallet.toolbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity

@Composable
fun MainCollapsingToolbar(
    isShimmer: Boolean,
    name: String?,
    mainData: MainScreenDataEntity
){
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(
            id = R.string.hello,
            name ?: stringResource(id = R.string.unknown)
        ),
        color = Color.White,
        fontWeight = FontWeight(700),
        style = MaterialTheme.typography.subtitle1
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        text = stringResource(id = R.string.main_text_welcome),
        color = Color.White,
        style = MaterialTheme.typography.subtitle2
    )

    CardWithBalance(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        balance = mainData.balanceEntity,
        isShimmer = isShimmer
    )

    CurrenciesCard(isShimmer = isShimmer)
}