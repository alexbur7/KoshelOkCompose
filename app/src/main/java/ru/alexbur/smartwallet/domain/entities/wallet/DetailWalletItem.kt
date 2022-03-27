package ru.alexbur.smartwallet.domain.entities.wallet

import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
import ru.alexbur.smartwallet.domain.enums.Currency

sealed class DetailWalletItem {

    data class Day(val day: String) : DetailWalletItem()

    data class Transaction(
        val id: Long,
        val category: CategoryEntity,
        val money: String,
        val time: String,
        val day: String,
        val currency: Currency
    ) : DetailWalletItem()

    companion object {
        val shimmerData = listOf(
            Day(
                day = ""
            ),
            Transaction(
                id = 1,
                category = CategoryEntity(
                    id = 0,
                    type = TypeOperation.SELECT_EXPENSE,
                    operation = "",
                    iconId = R.drawable.ic_coffee
                ),
                money = "",
                time = "",
                day = "",
                Currency.RUB
            ),
            Transaction(
                id = 2,
                category = CategoryEntity(
                    id = 0,
                    type = TypeOperation.SELECT_EXPENSE,
                    operation = "",
                    iconId = R.drawable.ic_coffee
                ),
                money = "",
                time = "",
                day = "",
                Currency.RUB
            ),
            Transaction(
                id = 3,
                category = CategoryEntity(
                    id = 0,
                    type = TypeOperation.SELECT_EXPENSE,
                    operation = "",
                    iconId = R.drawable.ic_coffee
                ),
                money = "",
                time = "",
                day = "",
                Currency.RUB
            ),
            Transaction(
                id = 4,
                category = CategoryEntity(
                    id = 0,
                    type = TypeOperation.SELECT_EXPENSE,
                    operation = "",
                    iconId = R.drawable.ic_coffee
                ),
                money = "",
                time = "",
                day = "",
                Currency.RUB
            )
        )
    }
}