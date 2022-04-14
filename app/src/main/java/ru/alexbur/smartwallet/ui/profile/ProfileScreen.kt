package ru.alexbur.smartwallet.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.profile.toolbar.MainCollapsingToolbar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.wallet.detailwallet.DetailWalletScreenFactory
import javax.inject.Inject

@Composable
fun ProfileScreen(
    navController: NavController,
    mainViewModel: ProfileViewModel = hiltViewModel()
) {
    val name =
        mainViewModel.nameFlow.collectAsState(initial = stringResource(id = R.string.unknown))
    val mainData = mainViewModel.mainScreenData.collectAsState()
    val state = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = BottomNavigationHeight)
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
                .padding(top = 24.dp, start = 24.dp, end = 24.dp)
        ) {

            MainCollapsingToolbar(
                isShimmer = mainData.value == MainScreenDataEntity.shimmerData,
                name = name.value,
                mainData = mainData.value
            )

            WalletsList(
                modifier = Modifier
                    .fillMaxWidth(),
                state = state,
                wallets = mainData.value.wallets
            ) {
                navController.navigate(DetailWalletScreenFactory.route + "/$it")
            }
        }
    }
}

class ProfileScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(route = route) {
            ProfileScreen(navGraph)
        }
    }
}