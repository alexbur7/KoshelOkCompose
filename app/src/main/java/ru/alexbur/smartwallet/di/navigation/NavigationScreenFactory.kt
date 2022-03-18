package ru.alexbur.smartwallet.di.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface NavigationScreenFactory {

    interface NavigationFactoryCompanion {
        val route: String
            get() = with(javaClass) {
                `package`.name + canonicalName
            }
    }

    fun create(
        builder: NavGraphBuilder,
        navGraph: NavHostController
    )
}