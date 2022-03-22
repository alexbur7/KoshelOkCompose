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
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.ui.utils.theme.ShadowNavBarColor
import ru.alexbur.smartwallet.ui.wallet.detailwallet.DetailWalletScreenFactory

@Composable
fun BottomNavBar(
    items: Array<NavItem.NavBarItems>,
    navController: NavController,
    viewModel: NavBarViewModel = hiltViewModel()
) {
    val navControllerBackStackEntry = navController.currentBackStackEntryAsState()
    val route = navControllerBackStackEntry.value?.destination?.route

    val loadState = viewModel.loadingState.collectAsState(LoadingState.LOAD_DEFAULT)
    val errorState = viewModel.errorState.collectAsState("")
    val walletIdState = viewModel.walletIdData.collectAsState(initial = -1L)

    val snackBarHostState = SnackbarHostState()

    LaunchedEffect(key1 = loadState.value) {
        when (loadState.value) {
            LoadingState.LOAD_FAILED -> {
                snackBarHostState.showSnackbar(
                    errorState.value,
                    duration = SnackbarDuration.Short
                )
            }
            LoadingState.LOAD_IN_PROGRESS -> {
            }
            LoadingState.LOAD_SUCCEED -> {
                navController.navigate(NavItem.NavBarItems.Profile.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
                navController.navigate("${DetailWalletScreenFactory.route}/${walletIdState.value}")
            }
            LoadingState.LOAD_DEFAULT -> {}
        }
    }

    Row(
        Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp))
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        ShadowNavBarColor,
                        Color.Transparent,
                    ),
                    endY = 28.dp.value
                )
            )
            .selectableGroup(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        items.forEach { tab ->
            val isSelected = tab.route == route
            BottomNavigationItem(
                modifier = Modifier,
                selected = isSelected,
                onClick = {
                    if (tab.route != route) {
                        with(navController) {
                            navigate(tab.route) {
                                popUpTo(graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
                            }
                        }
                    } else if (tab.route == route && tab == NavItem.NavBarItems.NewItem) {
                        viewModel.obtainEvent(NavBarViewModel.Event.CreateWalletStarted)
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

    SnackbarHost(hostState = snackBarHostState)
}