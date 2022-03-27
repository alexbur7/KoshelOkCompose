package ru.alexbur.smartwallet.domain.entities.wallet

import ru.alexbur.smartwallet.domain.entities.utils.CategoryEntity
import ru.alexbur.smartwallet.domain.entities.utils.TypeOperation
import ru.alexbur.smartwallet.domain.enums.Currency

data class TransactionEntity(
    val id: Long? = null,
    val idWallet: Long,
    var sum: String?,
    var type: TypeOperation?,
    var categoryEntity: CategoryEntity?,
    var date: Long?,
    var currency: Currency = Currency.RUB
)
