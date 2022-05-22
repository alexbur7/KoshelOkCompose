package ru.alexbur.smartwallet.domain.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class CurrencyScreenType(val code: Int): Parcelable {
    WALLET_SCREEN(0),
    TRANSACTION_SCREEN(1);

    companion object {
        fun convertCodeToType(code: Int): CurrencyScreenType {
            return values().toList().find { it.code == code }
                ?: throw NullPointerException()
        }
    }
}
