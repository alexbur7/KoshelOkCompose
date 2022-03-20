package ru.alexbur.smartwallet.ui.navbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(
    items: Array<NavItem.NavBarItems>,
    navController: NavController
) {
    val navControllerBackStackEntry = navController.currentBackStackEntryAsState()
    val route = navControllerBackStackEntry.value?.destination?.route

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items.forEach { tab ->
            /*BottomNavigationItem(
                modifier = Modifier,
                selected = tab.route == route,
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
                    Icon(
                        painter = painterResource(tab.icon),
                        contentDescription = null
                    )
                }
            )*/
        }
    }
}