package ru.alexbur.smartwallet.ui.wallet.detailwallet

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import dagger.hilt.android.EntryPointAccessors
import ru.alexbur.smartwallet.di.navigation.NavigationFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.domain.repositories.DeleteTransactionRepository
import ru.alexbur.smartwallet.domain.repositories.DetailWalletRepository
import ru.alexbur.smartwallet.ui.MainActivity
import javax.inject.Inject

private const val WALLET_ID_KEY = "walletId"

@Composable
fun DetailWalletScreen(
    navController: NavController,
    viewModel: DetailWalletViewModel
) {
}

@Composable
fun detailWalletViewModel(
    walletId: Long
): DetailWalletViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
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