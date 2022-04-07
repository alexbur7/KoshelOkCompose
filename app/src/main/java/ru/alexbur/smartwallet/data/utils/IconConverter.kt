package ru.alexbur.smartwallet.data.utils

import ru.alexbur.smartwallet.R
import javax.inject.Inject

class IconConverter @Inject constructor() {

    fun convertNumberToDrawableId(number: Int): Int {
        return when (number) {
            CAPITALIZATION -> R.drawable.capitalization
            FLY -> R.drawable.fly
            JEWELRY -> R.drawable.jewelry
            PRESENT -> R.drawable.present
            RESTAURANT -> R.drawable.restaurant
            SALARY -> R.drawable.salary
            SUPERMARKET -> R.drawable.supermarket
            BANK -> R.drawable.bank
            CAR -> R.drawable.car
            CLOTHES -> R.drawable.clothes
            COFFEE -> R.drawable.coffee
            COMMUNAL -> R.drawable.communal
            CONSTRUCTION -> R.drawable.construction
            EDUCATION -> R.drawable.education
            ENTERTAIMENTS -> R.drawable.entertainments
            FRIENDS -> R.drawable.friends
            GAMES -> R.drawable.games
            INTERNET -> R.drawable.internet
            MEDICINE -> R.drawable.ic_medicine
            MEDICINES -> R.drawable.medicines
            MOVIE -> R.drawable.movie
            MUSIC -> R.drawable.music
            PETS -> R.drawable.pets
            PHONE -> R.drawable.phone
            REST -> R.drawable.rest
            SAVINGS -> R.drawable.savings
            SPORT -> R.drawable.sport
            TRAIN -> R.drawable.train
            TV -> R.drawable.tv
            WIFI -> R.drawable.wifi
            TRANSPORT -> R.drawable.transport
            ETH -> R.drawable.eth
            else -> R.drawable.salary
        }
    }

    @Suppress("LongMethod", "ComplexMethod")
    fun convertDrawableIdToNumber(drawableId: Int): Int {
        return when (drawableId) {
            R.drawable.capitalization -> CAPITALIZATION
            R.drawable.fly -> FLY
            R.drawable.jewelry -> JEWELRY
            R.drawable.present -> PRESENT
            R.drawable.restaurant -> RESTAURANT
            R.drawable.salary -> SALARY
            R.drawable.supermarket -> SUPERMARKET
            R.drawable.wifi -> WIFI
            R.drawable.train -> TRAIN
            R.drawable.tv -> TV
            R.drawable.sport -> SPORT
            R.drawable.savings -> SAVINGS
            R.drawable.rest -> REST
            R.drawable.phone -> PHONE
            R.drawable.medicines -> MEDICINES
            R.drawable.pets -> PETS
            R.drawable.music -> MUSIC
            R.drawable.movie -> MOVIE
            R.drawable.ic_medicine -> MEDICINE
            R.drawable.internet -> INTERNET
            R.drawable.games -> GAMES
            R.drawable.friends -> FRIENDS
            R.drawable.entertainments -> ENTERTAIMENTS
            R.drawable.education -> EDUCATION
            R.drawable.construction -> CONSTRUCTION
            R.drawable.communal -> COMMUNAL
            R.drawable.coffee -> COFFEE
            R.drawable.clothes -> CLOTHES
            R.drawable.car -> CAR
            R.drawable.bank -> BANK
            R.drawable.transport -> TRANSPORT
            R.drawable.eth -> ETH
            else -> R.drawable.salary
        }
    }

    private companion object {
        const val CAPITALIZATION = 0
        const val FLY = 1
        const val JEWELRY = 2
        const val PRESENT = 3
        const val RESTAURANT = 4
        const val SALARY = 5
        const val SUPERMARKET = 6
        const val BANK = 7
        const val CAR = 8
        const val CLOTHES = 9
        const val COFFEE = 10
        const val COMMUNAL = 11
        const val CONSTRUCTION = 12
        const val EDUCATION = 13
        const val ENTERTAIMENTS = 14
        const val FRIENDS = 15
        const val GAMES = 16
        const val INTERNET = 17
        const val MEDICINE = 18
        const val MEDICINES = 19
        const val MOVIE = 20
        const val MUSIC = 21
        const val PETS = 22
        const val PHONE = 23
        const val REST = 24
        const val SAVINGS = 25
        const val SPORT = 26
        const val TRAIN = 27
        const val TV = 28
        const val WIFI = 29
        const val TRANSPORT = 30
        const val ETH = 31
    }
}