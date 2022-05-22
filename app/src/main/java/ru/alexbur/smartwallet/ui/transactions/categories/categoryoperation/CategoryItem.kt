package ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.enums.TypeOperation
import ru.alexbur.smartwallet.ui.utils.TextWithEndImage

@Composable
fun CategoryItem(
    modifier: Modifier,
    categoryEntity: CategoryEntity,
    isSelect: Boolean,
    onClick: (CategoryEntity) -> Unit
) {
    Row(modifier = modifier
        .clickable {
            onClick(categoryEntity)
        }
        .padding(vertical = 12.dp)) {
        Image(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
                .size(24.dp),
            painter = painterResource(id = categoryEntity.iconId),
            contentDescription = stringResource(id = R.string.category_image_description),
            contentScale = ContentScale.None
        )

        TextWithEndImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically),
            text = categoryEntity.operation,
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            imageId = if (isSelect) R.drawable.choose_icon else null
        )
    }
}

@Preview
@Composable
fun CategoryItemIsSelectPreview() {
    var isSelect by remember { mutableStateOf(false) }
    CategoryItem(
        modifier = Modifier.fillMaxWidth(),
        categoryEntity = CategoryEntity(
            0,
            TypeOperation.SELECT_INCOME,
            "Супермаркет",
            R.drawable.supermarket
        ),
        isSelect = isSelect,
        onClick = {
            isSelect = !isSelect
        })
}