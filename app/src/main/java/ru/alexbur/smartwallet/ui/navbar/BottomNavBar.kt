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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.alexbur.smartwallet.ui.utils.theme.ShadowNavBarColor

@Composable
fun BottomNavBar(
    items: Array<NavItem.NavBarItems>,
    navController: NavController,
    viewModel: NavBarViewModel = hiltViewModel()
) {
    val navControllerBackStackEntry = navController.currentBackStackEntryAsState()
    val route = navControllerBackStackEntry.value?.destination?.route

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
                    } else if (tab.route == route && tab == NavItem.NavBarItems.NewWallet) {
                        navController.navigate(NavItem.NavBarItems.Profile.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                        }
                        //TODO здесь надо сохранить данные, возмонжо стоит сделать в dispose эффекте на экрнае(проверить)
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


    /*BottomNavigation(
        modifier =
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)),
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
                                launchSingleTop = true
                                popUpTo(graph.startDestinationId) {
                                    saveState = true
                                }
                                restoreState = true
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
    }*/
}