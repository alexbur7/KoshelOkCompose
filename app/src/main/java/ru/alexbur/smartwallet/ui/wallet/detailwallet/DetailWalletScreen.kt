package ru.alexbur.smartwallet.ui.wallet.detailwallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.EntryPointAccessors
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.ui.MainActivity
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.utils.ConfirmAlertDialog
import ru.alexbur.smartwallet.ui.utils.SmartWalletSnackBar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.wallet.detailwallet.listtransactions.TransactionsBottomSheet
import ru.alexbur.smartwallet.ui.wallet.detailwallet.listwalletcard.FullCardWalletInDetail
import javax.inject.Inject

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
    val errorMessage = viewModel.errorMessage.collectAsState()
    val loadState = viewModel.loadStateData.collectAsState()
    val collapsingState = rememberCollapsingToolbarScaffoldState()
    val snackBarHostState = SnackbarHostState()
    var isFirstLaunch by remember {
        mutableStateOf(true)
    }
    var isShowDeleteDialog by remember {
        mutableStateOf(false)
    }
    var deleteTransactionId by remember {
        mutableStateOf<Long?>(null)
    }

    LaunchedEffect(key1 = pagerState.currentPage, errorMessage.value) {
        if (!isFirstLaunch) {
            viewModel.obtainEvent(
                DetailWalletViewModel.Event.OnLoadingTransactionStarted(
                    walletsState[pagerState.currentPage].id
                )
            )
        }
        if (errorMessage.value.isNotBlank()) {
            snackBarHostState.showSnackbar(
                message = errorMessage.value
            )
        }
        isFirstLaunch = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = BottomNavigationHeight - 4.dp)
            .background(BackgroundColor)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.main_background_image),
            contentDescription = "Background image",
            contentScale = ContentScale.FillWidth
        )

        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = collapsingState,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.close_screen),
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
                }
            }
        ) {
            TransactionsBottomSheet(
                modifier = Modifier
                    .fillMaxSize(),
                state = state,
                transactions = transactionState.value,
                isShimmer = transactionState.value == DetailWalletItem.shimmerData,
                editItem = {
                    // TODO подставить методы из vm
                },
                deleteItem = {
                    deleteTransactionId = it
                    isShowDeleteDialog = true
                }
            )
        }

        if (isShowDeleteDialog) {
            ConfirmAlertDialog(
                modifier = Modifier,
                text = stringResource(id = R.string.delete_title),
                onConfirm = {
                    deleteTransactionId?.let {
                        viewModel.obtainEvent(DetailWalletViewModel.Event.DeleteTransaction(it))
                    }
                    deleteTransactionId = null
                    isShowDeleteDialog = false
                },
                onDismiss = {
                    isShowDeleteDialog = false
                }
            )
        }

        SnackbarHost(hostState = snackBarHostState) {
            SmartWalletSnackBar(snackbarData = it)
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
    companion object Companion : NavigationFactory.NavigationFactoryCompanion {
        private const val WALLET_ID_KEY = "walletId"
    }

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