package ru.alexbur.smartwallet.ui.filter.wallets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
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
import ru.alexbur.smartwallet.ui.filter.FilterToolbar
import ru.alexbur.smartwallet.ui.filter.transactions.FilterTransactionsViewModel
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.profile.WalletsList
import ru.alexbur.smartwallet.ui.utils.ConfirmAlertDialog
import ru.alexbur.smartwallet.ui.utils.SmartWalletSnackBar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import javax.inject.Inject

@Composable
fun FilterWalletsScreen(
    navController: NavController,
    viewModel: FilterWalletsViewModel = hiltViewModel()
) {
    val walletsState = viewModel.walletsData.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()
    val snackBarHostState = SnackbarHostState()
    val state = rememberLazyListState()
    var isShowDeleteDialog by remember {
        mutableStateOf(false)
    }
    var deleteWalletId by remember {
        mutableStateOf<Long?>(null)
    }

    LaunchedEffect(key1 = errorMessage.value) {
        if (errorMessage.value.isNotBlank()) {
            snackBarHostState.showSnackbar(
                message = errorMessage.value
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.obtainEvent(FilterWalletsViewModel.Event.OnLoadingStarted)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            FilterToolbar(modifier = Modifier.fillMaxWidth(), filter = {
                viewModel.obtainEvent(FilterWalletsViewModel.Event.FilterWallets(it))
            })
            WalletsList(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 12.dp),
                state = state,
                wallets = walletsState.value,
                clickItem = {},
                deleteItem = {
                    deleteWalletId = it
                    isShowDeleteDialog = true
                },
                editItem = {
                    viewModel.obtainEvent(FilterWalletsViewModel.Event.EditWallet(it))
                }
            )
        }

        if (isShowDeleteDialog) {
            ConfirmAlertDialog(
                modifier = Modifier,
                text = stringResource(id = R.string.delete_title),
                onConfirm = {
                    deleteWalletId?.let {
                        viewModel.obtainEvent(FilterWalletsViewModel.Event.DeleteWallet(it))
                    }
                    deleteWalletId = null
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

class FilterWalletsScreenFactory @Inject constructor() : NavigationScreenFactory {

    companion object Companion : NavigationFactory.NavigationFactoryCompanion

    override val factoryType: List<NavigationFactory.NavigationFactoryType>
        get() = listOf(NavigationFactory.NavigationFactoryType.Nested)

    override fun create(builder: NavGraphBuilder, navGraph: NavHostController) {
        builder.composable(
            route = route
        ) {
            FilterWalletsScreen(navController = navGraph)
        }
    }
}