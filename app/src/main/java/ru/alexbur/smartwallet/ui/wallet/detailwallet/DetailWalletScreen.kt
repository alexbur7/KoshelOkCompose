package ru.alexbur.smartwallet.ui.wallet.detailwallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.EntryPointAccessors
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.ui.MainActivity
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.wallet.detailwallet.listtransactions.TransactionsList
import ru.alexbur.smartwallet.ui.wallet.detailwallet.listwalletcard.FullCardWalletInDetail
import javax.inject.Inject

private const val WALLET_ID_KEY = "walletId"

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailWalletScreen(
    navController: NavController,
    viewModel: DetailWalletViewModel
) {
    val walletsState by viewModel.walletsData.collectAsState()
    val transactionState = viewModel.transitionsData.collectAsState()
    val pagerState =
        rememberPagerState(initialPage = if (viewModel.positionWallet >= 0) viewModel.positionWallet else 0)
    val state = rememberLazyListState()
    var isFirstLaunch by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        if (!isFirstLaunch) {
            viewModel.obtainEvent(
                DetailWalletViewModel.Event.OnLoadingTransactionStarted(
                    walletsState[pagerState.currentPage].id
                )
            )
        }
        isFirstLaunch = false
    }

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
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.close_detail_wallet),
                contentDescription = "Currency close",
                modifier = Modifier
                    .wrapContentHeight()
                    .wrapContentWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.Start)
                    .clickable {
                        navController.popBackStack()
                    }
            )

            FullCardWalletInDetail(
                pagerState = pagerState,
                wallets = walletsState,
                isShimmer = walletsState == WalletEntity.shimmerData
            )

            TransactionsList(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxSize(),
                state = state,
                transactions = transactionState.value,
                isShimmer = transactionState.value == DetailWalletItem.shimmerData
            )
        }
    }
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