package ru.alexbur.smartwallet.domain.entities.wallet

import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import ru.alexbur.smartwallet.domain.enums.TypeOperation

data class TransactionEntity(
    val id: Long? = null,
    val idWallet: Long,
    val sum: String,
    val type: TypeOperation,
    val categoryEntity: CategoryEntity,
    val date: Long,
    val currency: CurrencyEntity
)
