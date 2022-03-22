package ru.alexbur.smartwallet.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.listwallet.MainScreenDataEntity
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.ui.profile.toolbar.MainCollapsingToolbar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import javax.inject.Inject

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: ProfileViewModel = hiltViewModel()
) {
    val name =
        mainViewModel.nameFlow.collectAsState(initial = stringResource(id = R.string.unknown))
    val mainData = mainViewModel.mainScreenData.collectAsState()
    val state = rememberLazyListState()
    val isShimmer = mainData.value == MainScreenDataEntity.shimmerData

    Box(modifier = Modifier
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
}

@Composable
fun WalletItem(
    walletEntity: WalletEntity,
    isShimmer: Boolean
) {

}

class ListWalletScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route) {
            MainScreen(navGraph)
        }
    }
}