package ru.alexbur.smartwallet.data.utils

import ru.alexbur.smartwallet.R
import javax.inject.Inject

class IconConverter @Inject constructor() {

    fun convertNumberToDrawableId(number: Int?): Int {
        return iconCodeMap[number] ?: R.drawable.salary
    }

    fun convertDrawableIdToNumber(drawableId: Int): Int {
        return iconCodeMap.entries.find { it.value == drawableId }?.key ?: 0
    }

    companion object {
        private val iconCodeMap = mapOf(
            0 to R.drawable.capitalization,
            1 to R.drawable.fly,
            2 to R.drawable.jewelry,
            3 to R.drawable.present,
            4 to R.drawable.restaurant,
            5 to R.drawable.salary,
            6 to R.drawable.supermarket,
            7 to R.drawable.bank,
            8 to R.drawable.car,
            9 to R.drawable.clothes,
            10 to R.drawable.coffee,
            11 to R.drawable.communal,
            12 to R.drawable.construction,
            13 to R.drawable.education,
            14 to R.drawable.entertainments,
            15 to R.drawable.friends,
            16 to R.drawable.games,
            17 to R.drawable.internet,
            18 to R.drawable.ic_medicine,
            19 to R.drawable.medicines,
            20 to R.drawable.movie,
            21 to R.drawable.music,
            22 to R.drawable.pets,
            23 to R.drawable.phone,
            24 to R.drawable.rest,
            25 to R.drawable.savings,
            26 to R.drawable.sport,
            27 to R.drawable.train,
            28 to R.drawable.tv,
            29 to R.drawable.wifi,
            30 to R.drawable.transport,
            31 to R.drawable.eth,
        )

        val COUNT_IMAGE = iconCodeMap.size
    }
}