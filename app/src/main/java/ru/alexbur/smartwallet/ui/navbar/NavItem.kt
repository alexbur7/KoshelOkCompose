package ru.alexbur.smartwallet.ui.navbar

import androidx.annotation.DrawableRes
import ru.alexbur.smartwallet.R
import ru.alexbur.smartwallet.ui.auth.AuthorizationScreenFactory
import ru.alexbur.smartwallet.ui.main.MainNavHostScreenFactory
import ru.alexbur.smartwallet.ui.profile.ProfileScreenFactory
import ru.alexbur.smartwallet.ui.search.SearchWalletScreenFactory
import ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation.CategoriesScreenFactory
import ru.alexbur.smartwallet.ui.transactions.createtransaction.CreateTransactionScreenFactory
import ru.alexbur.smartwallet.ui.wallet.createwallet.CreateWalletScreenFactory
import ru.alexbur.smartwallet.ui.wallet.createwallet.listcurrency.CurrenciesScreenFactory
import ru.alexbur.smartwallet.ui.wallet.detailwallet.DetailWalletScreenFactory

sealed class NavItem(val route: String) {

    object Authorization : NavItem(AuthorizationScreenFactory.route)

    object MainScreen : NavItem(MainNavHostScreenFactory.route)

    enum class NavBarItems(
        @DrawableRes
        val icon: Int,
        @DrawableRes
        val selectedIcon: Int,
        val route: String,
        val nestedRoute: List<String>
    ) {
        Profile(
            R.drawable.profile_icon,
            R.drawable.profile_choose_icon,
            ProfileScreenFactory.route,
            listOf(DetailWalletScreenFactory.route)
        ),

        NewItem(
            R.drawable.new_wallet_icon,
            R.drawable.new_wallet_confirm_icon,
            CreateWalletScreenFactory.route,
            listOf(
                CreateTransactionScreenFactory.route,
                CurrenciesScreenFactory.route,
                CategoriesScreenFactory.route
            )
        ),

        Search(
            R.drawable.search_icon,
            R.drawable.search_choose_icon,
            SearchWalletScreenFactory.route,
            emptyList()
        )
    }
}