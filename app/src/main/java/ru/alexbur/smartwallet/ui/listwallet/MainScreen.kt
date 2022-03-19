package ru.alexbur.smartwallet.ui.listwallet

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import javax.inject.Inject

@Composable
fun MainScreen(
    navController: NavController? = null,
    mainViewModel: MainViewModel = hiltViewModel()
) {
}

class MainScreenFactory @Inject constructor(): NavigationScreenFactory{

    companion object Companion : NavigationScreenFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route){
            MainScreen(navGraph)
        }
    }

}