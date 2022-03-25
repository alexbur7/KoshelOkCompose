package ru.alexbur.smartwallet.ui.wallet.detailwallet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.EntryPointAccessors
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.ui.MainActivity
import ru.alexbur.smartwallet.ui.wallet.detailwallet.listwalletcard.FullCardWalletInDetail
import javax.inject.Inject

private const val WALLET_ID_KEY = "walletId"

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailWalletScreen(
    navController: NavController,
    viewModel: DetailWalletViewModel
) {
    val walletsState = viewModel.walletsData.collectAsState()
    val pagerState =
        rememberPagerState(initialPage = if (viewModel.positionWallet >= 0) viewModel.positionWallet else 0)

    FullCardWalletInDetail(
        pagerState = pagerState,
        wallets = walletsState.value,
        isShimmer = walletsState.value == WalletEntity.shimmerData
    )
}

@Composable
fun detailWalletViewModel(
    walletId: Long
): DetailWalletViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as MainActivity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).noteDetailViewModelFactory()

    return viewModel(
        factory = DetailWalletViewModel.provideFactory(
            factory,
            walletId = walletId
        )
    )
}

class DetailWalletScreenFactory @Inject constructor() : NavigationScreenFactory {
    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = "$route/{${
                WALLET_ID_KEY
            }}",
            arguments = listOf(navArgument(WALLET_ID_KEY) { type = NavType.LongType })
        ) {
            it.arguments?.getLong(WALLET_ID_KEY)?.let { walletId ->
                DetailWalletScreen(
                    navController = navGraph,
                    viewModel = detailWalletViewModel(
                        walletId = walletId
                    )
                )
            }
        }
    }

}