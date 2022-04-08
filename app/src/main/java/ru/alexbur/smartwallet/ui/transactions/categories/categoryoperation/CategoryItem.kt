package ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
import ru.alexbur.smartwallet.ui.utils.CustomRadioButton

@Composable
fun CategoryItem(
    modifier: Modifier,
    categoryEntity: CategoryEntity,
    isSelect: Boolean,
    onClick: (CategoryEntity) -> Unit
) {
    Row(modifier = modifier
        .padding(vertical = 12.dp)) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(24.dp),
            painter = painterResource(id = categoryEntity.iconId),
            contentDescription = stringResource(id = R.string.category_image_description),
            contentScale = ContentScale.None
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically), text = categoryEntity.operation,
            style = TextStyle(color = Color.White, fontSize = 16.sp)
        )

        CustomRadioButton(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp), isSelect = isSelect
        ) {
            onClick(categoryEntity)
        }
    }
}

@Preview
@Composable
fun CategoryItemIsSelectPreview() {
    CategoryItem(
        modifier = Modifier.fillMaxWidth(),
        categoryEntity = CategoryEntity(
            0,
            TypeOperation.SELECT_INCOME,
            "Супермаркет",
            R.drawable.supermarket
        ),
        isSelect = true,
        onClick = {})
}