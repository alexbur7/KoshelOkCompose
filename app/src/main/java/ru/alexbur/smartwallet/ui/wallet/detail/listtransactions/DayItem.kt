package ru.alexbur.smartwallet.ui.wallet.detail.listtransactions

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundMainCardFirstColor
import ru.alexbur.smartwallet.ui.utils.theme.ShimmerPlaceHolderColor

@Composable
fun DayItem(modifier: Modifier,dayItem: DetailWalletItem.Day, isShimmer: Boolean) {
    Text(
        modifier = modifier
            .placeholder(
                visible = isShimmer,
                color = BackgroundMainCardFirstColor,
                highlight = PlaceholderHighlight.shimmer(
                    highlightColor = ShimmerPlaceHolderColor
                ), shape = RoundedCornerShape(8.dp)
            ),
        text = dayItem.day,
        style = TextStyle(Color.White, fontSize = 16.sp, fontWeight = FontWeight(700))
    )
}