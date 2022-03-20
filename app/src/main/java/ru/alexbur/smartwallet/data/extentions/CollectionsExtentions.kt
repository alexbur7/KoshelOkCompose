package ru.alexbur.smartwallet.data.extentions

import ru.alexbur.smartwallet.di.navigation.NavigationFactory

fun Set<NavigationFactory>.filter(vararg filters: NavigationFactory.NavigationFactoryType): List<NavigationFactory> {
    val rezList = mutableListOf<NavigationFactory>()
    filters.forEach { type ->
        rezList.addAll(this.filter { it.factoryType.contains(type) })
    }
    return rezList.toList()
}