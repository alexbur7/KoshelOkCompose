package ru.alexbur.koshelok.domain.entities.wallet

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.alexbur.koshelok.domain.entities.utils.CategoryEntity
import ru.alexbur.koshelok.domain.enums.Currency
import ru.alexbur.koshelok.domain.entities.utils.TypeOperation

@Parcelize
data class TransactionEntity(
    val id: Long? = null,
    val idWallet: Long,
    var sum: String?,
    var type: TypeOperation?,
    var categoryEntity: CategoryEntity?,
    var date: Long?,
    var currency: Currency = Currency.RUB
) : Parcelable
