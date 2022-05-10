package ru.alexbur.smartwallet.ui.filter.transactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.ui.filter.FilterToolbar
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.utils.SmartWalletSnackBar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.wallet.detailwallet.listtransactions.TransactionsList
import javax.inject.Inject

@Composable
fun FilterTransactionsScreen(
    navController: NavController,
    viewModel: FilterTransactionsViewModel = hiltViewModel()
) {
    val transactions = viewModel.transitionsData.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()
    val snackBarHostState = SnackbarHostState()
    val state = rememberLazyListState()

    LaunchedEffect(key1 = errorMessage.value) {
        if (errorMessage.value.isNotBlank()) {
            snackBarHostState.showSnackbar(
                message = errorMessage.value
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = BottomNavigationHeight - 4.dp)
            .background(BackgroundColor)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.main_background_image),
            contentDescription = "Background image",
            contentScale = ContentScale.FillWidth
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            FilterToolbar(modifier = Modifier.fillMaxWidth(), filter = {
                viewModel.obtainEvent(FilterTransactionsViewModel.Event.FilterTransaction(it))
            })

            TransactionsList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp),
                state = state,
                transactions = transactions.value,
                isShimmer = transactions.value == DetailWalletItem.shimmerData
            )
        }

        SnackbarHost(hostState = snackBarHostState) {
            SmartWalletSnackBar(snackbarData = it)
        }
    }
}

class FilterTransactionsScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            FilterTransactionsScreen(navController = navGraph)
        }
    }
}