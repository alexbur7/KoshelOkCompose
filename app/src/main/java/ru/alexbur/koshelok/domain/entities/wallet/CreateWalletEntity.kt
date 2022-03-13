package ru.alexbur.koshelok.domain.entities.wallet

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.alexbur.koshelok.domain.enums.Currency

@Parcelize
data class CreateWalletEntity(
    val id: Long? = null,
    var limit: String? = null,
    var name: String,
    var currency: Currency,
) : Parcelable
