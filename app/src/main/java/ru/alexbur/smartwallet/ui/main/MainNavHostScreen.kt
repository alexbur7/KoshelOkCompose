package ru.alexbur.smartwallet.ui.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.alexbur.smartwallet.data.extentions.filter
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationHostFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.navbar.BottomNavBar
import ru.alexbur.smartwallet.ui.navbar.NavBarViewModel
import ru.alexbur.smartwallet.ui.navbar.NavItem
import javax.inject.Inject

@Composable
fun MainNavHostScreen(
    modifier: Modifier,
    navigationFactoryList: List<NavigationFactory>,
) {
    val controller = rememberNavController()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavBar(
                items = NavItem.NavBarItems.values(),
                navController = controller
            )
        }
    ) {
        NavHost(
            navController = controller,
            startDestination = NavItem.NavBarItems.Profile.route
        ) {
            navigationFactoryList
                .forEach { it.create(this, controller) }
        }
    }
}


class MainNavHostScreenFactory @Inject constructor(
    private val navigationFactorySet: @JvmSuppressWildcards Set<NavigationScreenFactory>
) : NavigationHostFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Main)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route) {
            MainNavHostScreen(
                modifier = Modifier
                    .navigationBarsPadding(),
                navigationFactoryList = navigationFactorySet
                    .filter(NavigationFactory.NavigationFactoryType.Nested)
            )
        }
    }
}