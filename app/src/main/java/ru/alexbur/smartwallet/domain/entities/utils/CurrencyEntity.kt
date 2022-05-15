package ru.alexbur.smartwallet.domain.entities.utils

data class CurrencyEntity(
    val id: Long? = null,
    val name: String,
    val course: String,
    val fullName: String,
    val fullListName: String,
    val icon: String,
    val isUp: Boolean
){
    companion object{
        val default = CurrencyEntity(
            id = 1,
            name = "RUB",
            course = "1",
            fullListName = "",
            fullName = "",
            icon = "₽",
            isUp = false
        )
    }
}
