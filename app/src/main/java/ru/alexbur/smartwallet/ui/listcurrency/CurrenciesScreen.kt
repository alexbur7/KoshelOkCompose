package ru.alexbur.smartwallet.ui.listcurrency

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.theme.BackgroundColor
import javax.inject.Inject

@Composable
fun CurrenciesScreen(
    navController: NavController,
    viewModel: CurrenciesViewModel = hiltViewModel()
) {
    val currency = viewModel.currentCurrency.collectAsState()

    var currentCurrency by rememberSaveable {
        mutableStateOf(currency.value)
    }

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    )
}

class CurrenciesScreenNavigation @Inject constructor() : NavigationScreenFactory {
    companion object Companion : NavigationScreenFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route) {
            CurrenciesScreen(navController = navGraph)
        }
    }

}