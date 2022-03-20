package ru.alexbur.smartwallet.ui.listcurrency

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.theme.BackgroundColor
import javax.inject.Inject

@Composable
fun CurrenciesScreen(
    navController: NavController,
    viewModel: CurrenciesViewModel = hiltViewModel()
) {

    val currencies = viewModel.currencies.collectAsState()

    var currentCurrency by rememberSaveable {
        mutableStateOf(viewModel.currentCurrency.value)
    }

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.main_background_image),
            contentDescription = "Background image",
            contentScale = ContentScale.FillWidth
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            CurrencyToolbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                close = {
                    navController.popBackStack()
                }, {
                    viewModel.obtainEvent(CurrenciesViewModel.Event.ChooseCurrency(currentCurrency))
                }
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 8.dp),
            ) {
                val currentIndex = currencies.value.indexOf(currentCurrency)
                items(
                    currencies.value.size
                ) { position ->
                    val isCurrent = position == currentIndex
                    CurrencyItem(
                        currency = currencies.value[position],
                        isCurrent = isCurrent,
                        onCheckedChange = { currency ->
                            currentCurrency = currency
                        }
                    )
                }
            }
        }
    }
}

class CurrenciesScreenNavigation @Inject constructor() : NavigationScreenFactory {
    companion object Companion : NavigationScreenFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route) {
            CurrenciesScreen(navController = navGraph)
        }
    }

}