package ru.alexbur.smartwallet.ui.listwallet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.ui.listwallet.toolbar.MainCollapsingToolbar
import ru.alexbur.smartwallet.ui.theme.BackgroundColor
import javax.inject.Inject

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val name =
        mainViewModel.nameFlow.collectAsState(initial = stringResource(id = R.string.unknown))
    val mainData = mainViewModel.mainScreenData.collectAsState()
    val state = rememberLazyListState()
    val isShimmer = mainData.value == MainScreenDataEntity.shimmerData

    Column(
        Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .padding(24.dp)
    ) {

        MainCollapsingToolbar(
            isShimmer = isShimmer,
            name = name.value,
            mainData = mainData.value
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            state = state
        ) {
            items(
                mainData.value.wallets.size
            ) { index ->
                WalletItem(
                    mainData.value.wallets[index],
                    isShimmer = isShimmer
                )
            }
        }
    }
}

@Composable
fun WalletItem(
    walletEntity: WalletEntity,
    isShimmer: Boolean
) {

}

class MainScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationScreenFactory.NavigationFactoryCompanion

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route) {
            MainScreen(navGraph)
        }
    }
}