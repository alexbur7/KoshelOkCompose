package ru.alexbur.smartwallet.domain.entities.wallet

import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import ru.alexbur.smartwallet.domain.enums.TypeOperation

sealed class DetailWalletItem {

    data class Day(val day: String) : DetailWalletItem()

    data class Transaction(
        val id: Long,
        val category: CategoryEntity,
        val money: String,
        val time: String,
        val day: String,
        val walletId: Long,
        val timeStamp: Long,
        val currency: CurrencyEntity
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
                    iconId = R.drawable.coffee
                ),
                money = "",
                time = "",
                day = "",
                walletId = 0,
                timeStamp = 1,
                currency = CurrencyEntity.default
            ),
            Transaction(
                id = 2,
                category = CategoryEntity(
                    id = 0,
                    type = TypeOperation.SELECT_EXPENSE,
                    operation = "",
                    iconId = R.drawable.coffee
                ),
                money = "",
                time = "",
                day = "",
                walletId = 0,
                timeStamp = 1,
                currency = CurrencyEntity.default
            ),
            Transaction(
                id = 3,
                category = CategoryEntity(
                    id = 0,
                    type = TypeOperation.SELECT_EXPENSE,
                    operation = "",
                    iconId = R.drawable.coffee
                ),
                money = "",
                time = "",
                day = "",
                walletId = 0,
                timeStamp = 1,
                currency = CurrencyEntity.default
            ),
            Transaction(
                id = 4,
                category = CategoryEntity(
                    id = 0,
                    type = TypeOperation.SELECT_EXPENSE,
                    operation = "",
                    iconId = R.drawable.coffee
                ),
                money = "",
                time = "",
                day = "",
                walletId = 0,
                timeStamp = 1,
                currency = CurrencyEntity.default
            )
        )
    }
}