package ru.alexbur.smartwallet.ui.navbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.ui.filter.transactions.FilterTransactionsScreenFactory
import ru.alexbur.smartwallet.ui.profile.ProfileScreenFactory
import ru.alexbur.smartwallet.ui.transactions.categories.createcategory.CreateCategoryScreenFactory
import ru.alexbur.smartwallet.ui.transactions.createtransaction.CreateTransactionScreenFactory
import ru.alexbur.smartwallet.ui.utils.SmartWalletSnackBar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.utils.theme.ShadowNavBarColor
import ru.alexbur.smartwallet.ui.wallet.createwallet.CreateWalletScreenFactory
import ru.alexbur.smartwallet.ui.wallet.detailwallet.DetailWalletScreenFactory

@Composable
fun BottomNavBar(
    items: Array<NavItem.NavBarItems>,
    navController: NavController,
    viewModel: NavBarViewModel = hiltViewModel()
) {

    val navControllerBackStackEntry by navController.currentBackStackEntryAsState()
    val route = navControllerBackStackEntry?.destination?.route

    val loadState = viewModel.loadingState.collectAsState(LoadingState.LOAD_DEFAULT)
    val errorState = viewModel.errorState.collectAsState()
    val walletIdState = viewModel.walletIdData.collectAsState()

    val snackBarHostState = SnackbarHostState()

    LaunchedEffect(key1 = loadState.value) {
        when (loadState.value) {
            LoadingState.LOAD_FAILED -> {
                // TODO завершить прогресс
                snackBarHostState.showSnackbar(
                    errorState.value
                )
            }
            LoadingState.LOAD_IN_PROGRESS -> {
                // TODO поставить прогресс
            }
            LoadingState.LOAD_SUCCEED -> {
                // TODO завершить прогресс
                if (route?.contains(CreateCategoryScreenFactory.route) == true) {
                    navController.popBackStack()
                } else {
                    navController.navigate(NavItem.NavBarItems.Profile.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                    navController.navigate("${DetailWalletScreenFactory.route}/${walletIdState.value}")
                }
            }
            LoadingState.LOAD_DEFAULT -> {}
        }
    }

    Row(
        Modifier
            .fillMaxWidth()
            .height(BottomNavigationHeight)
            .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp))
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        ShadowNavBarColor,
                        BackgroundColor,
                    ),
                    endY = 28.dp.value
                )
            )
            .selectableGroup(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items.forEach { tab ->
            val isSelected =
                tab.route == route || tab.nestedRoute.find { route?.contains(it) ?: false } != null
            BottomNavigationItem(
                modifier = Modifier,
                selected = isSelected,
                onClick = {
                    if (tab.route != route && tab.nestedRoute.find { route?.contains(it) == true } == null) {
                        val newRoute = if (tab == NavItem.NavBarItems.NewItem) {
                            if (route == ProfileScreenFactory.route) tab.route
                            else {
                                "${CreateTransactionScreenFactory.route}/${walletIdState.value}"
                            }
                        } else if (tab == NavItem.NavBarItems.Search) {
                            if (route == ProfileScreenFactory.route) tab.route
                            else {
                                FilterTransactionsScreenFactory.route
                            }
                        } else {
                            tab.route
                        }
                        with(navController) {
                            navigate(newRoute) {
                                popUpTo(graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                            }
                        }
                    } else if (tab.nestedRoute.find { route?.contains(it) == true } != null && tab == NavItem.NavBarItems.NewItem) {
                        when {
                            route?.contains(CreateWalletScreenFactory.route) == true -> {
                                viewModel.obtainEvent(NavBarViewModel.Event.CreateWalletStarted)
                            }
                            route?.contains(CreateCategoryScreenFactory.route) == true -> {
                                viewModel.obtainEvent(NavBarViewModel.Event.CreateCategoryStarted)
                            }
                            else -> {
                                viewModel.obtainEvent(NavBarViewModel.Event.CreateTransactionStarted)
                            }
                        }
                    }
                },
                icon = {
                    Image(
                        painter = painterResource(id = if (isSelected) tab.selectedIcon else tab.icon),
                        contentDescription = "Image in bottom navigation image"
                    )
                }
            )
        }
    }

    SnackbarHost(hostState = snackBarHostState) {
        SmartWalletSnackBar(snackbarData = it)
    }
}

val BottomNavigationHeight = 56.dp