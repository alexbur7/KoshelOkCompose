package ru.alexbur.smartwallet.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.enums.LoadingState
import ru.alexbur.smartwallet.ui.navbar.BottomNavigationHeight
import ru.alexbur.smartwallet.ui.utils.ConfirmAlertDialog
import ru.alexbur.smartwallet.ui.utils.SmartWalletSnackBar
import ru.alexbur.smartwallet.ui.utils.theme.BackgroundColor
import ru.alexbur.smartwallet.ui.wallet.detail.DetailWalletScreenFactory
import javax.inject.Inject

@Composable
fun ProfileScreen(
    navController: NavController,
    mainViewModel: ProfileViewModel = hiltViewModel()
) {
    val name =
        mainViewModel.nameFlow.collectAsState(initial = stringResource(id = R.string.unknown))
    val mainData = mainViewModel.mainScreenData.collectAsState()
    val loadState = mainViewModel.loadStateData.collectAsState()
    val errorMessage = mainViewModel.errorMessage.collectAsState("")
    val snackBarHostState = SnackbarHostState()
    var isShowDeleteDialog by remember {
        mutableStateOf(false)
    }
    var deleteWalletId by remember {
        mutableStateOf<Long?>(null)
    }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

    LaunchedEffect(key1 = loadState.value) {
        when (loadState.value) {
            LoadingState.LOAD_SUCCEED -> swipeRefreshState.isRefreshing =
                false
            LoadingState.LOAD_FAILED -> {
                swipeRefreshState.isRefreshing =
                    false
                if (errorMessage.value.isNotBlank()) {
                    snackBarHostState.showSnackbar(
                        message = errorMessage.value
                    )
                }
            }
            else -> Unit
        }
    }

    SwipeRefresh(state = swipeRefreshState, onRefresh = {
        mainViewModel.obtainEvent(ProfileViewModel.Event.OnLoadingNetworkStarted)
    }) {
        ProfileFullScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = BottomNavigationHeight)
                .background(BackgroundColor),
            mainData = mainData.value,
            name = name.value,
            clickItem = {
                navController.navigate(DetailWalletScreenFactory.route + "/$it")
            },
            editItem = {
                mainViewModel.obtainEvent(ProfileViewModel.Event.EditWallet(it))
            },
            deleteItem = {
                deleteWalletId = it
                isShowDeleteDialog = true
            }
        )

        if (isShowDeleteDialog) {
            ConfirmAlertDialog(
                modifier = Modifier,
                text = stringResource(id = R.string.delete_title),
                onConfirm = {
                    deleteWalletId?.let {
                        mainViewModel.obtainEvent(ProfileViewModel.Event.DeleteWallet(it))
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