package ru.alexbur.smartwallet.ui.wallet.createwallet.listcurrency

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
import androidx.navigation.*
import androidx.navigation.compose.composable
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.utils.CurrencyEntity
import ru.alexbur.smartwallet.domain.enums.CurrencyScreenType
import ru.alexbur.smartwallet.ui.utils.ChooseDataToolbar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import javax.inject.Inject

@Composable
fun CurrenciesScreen(
    currencyScreenType: CurrencyScreenType,
    navController: NavController,
    viewModel: CurrenciesViewModel = hiltViewModel()
) {

    val currencies = viewModel.currencies.collectAsState()

    var currentCurrency by rememberSaveable {
        mutableStateOf(
            when (currencyScreenType) {
                CurrencyScreenType.WALLET_SCREEN -> viewModel.createWalletFlow.value?.currency
                CurrencyScreenType.TRANSACTION_SCREEN -> viewModel.createTransactionEntity.value?.currency
            } ?: CurrencyEntity.default
        )
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
            ChooseDataToolbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                close = {
                    navController.popBackStack()
                },
                done = {
                    viewModel.obtainEvent(
                        CurrenciesViewModel.Event.ChooseCurrency(
                            currencyScreenType,
                            currentCurrency
                        )
                    )
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
                        modifier = Modifier.fillMaxWidth(),
                        currency = currencies.value[position],
                        isSelect = isCurrent,
                        onClick = { currency ->
                            currentCurrency = currency
                        }
                    )
                }
            }
        }
    }
}

class CurrenciesScreenFactory @Inject constructor() : NavigationScreenFactory {
    companion object Companion : NavigationFactory.NavigationFactoryCompanion {
        private const val CURRENCY_SCREEN_TYPE_CODE_KEY = "currency_screen_type_code_key"
    }

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = "$route/{${
                CURRENCY_SCREEN_TYPE_CODE_KEY
            }}", arguments = listOf(
                navArgument(
                    CURRENCY_SCREEN_TYPE_CODE_KEY
                ) { type = NavType.IntType })
        ) {
            it.arguments?.getInt(CURRENCY_SCREEN_TYPE_CODE_KEY)
                ?.let { code ->
                    CurrenciesScreen(
                        currencyScreenType = CurrencyScreenType.convertCodeToType(code),
                        navController = navGraph
                    )
                }
        }
    }

}