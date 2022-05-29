package ru.alexbur.smartwallet.domain.enums

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class CurrencyScreenType(val code: Int): Parcelable {
    CREATE_WALLET_SCREEN(0),
    CREATE_TRANSACTION_SCREEN(1),
    EDIT_WALLET_SCREEN(2),
    EDIT_TRANSACTION_SCREEN(3);

    companion object {
        fun convertCodeToType(code: Int): CurrencyScreenType {
            return values().toList().find { it.code == code }
                ?: throw NullPointerException()
        }
    }
}
