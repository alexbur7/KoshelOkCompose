package ru.alexbur.smartwallet.domain.entities.wallet

import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
import ru.alexbur.smartwallet.domain.enums.Currency

data class TransactionEntity(
    val id: Long? = null,
    val idWallet: Long,
    val sum: String,
    val type: TypeOperation,
    val categoryEntity: CategoryEntity,
    val date: Long,
    val currency: Currency = Currency.RUB
)
