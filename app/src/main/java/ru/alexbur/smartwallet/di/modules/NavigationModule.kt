package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import ru.alexbur.smartwallet.di.navigation.NavigationHostFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.auth.AuthorizationScreenFactory
import ru.alexbur.smartwallet.ui.main.MainNavHostScreenFactory
import ru.alexbur.smartwallet.ui.profile.ProfileScreenFactory
import ru.alexbur.smartwallet.ui.search.SearchWalletScreenFactory
import ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation.CategoriesScreenFactory
import ru.alexbur.smartwallet.ui.transactions.createtransaction.CreateTransactionScreenFactory
import ru.alexbur.smartwallet.ui.wallet.createwallet.CreateWalletScreenFactory
import ru.alexbur.smartwallet.ui.wallet.createwallet.listcurrency.CurrenciesScreenFactory
import ru.alexbur.smartwallet.ui.wallet.detailwallet.DetailWalletScreenFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @IntoSet
    @Binds
    @Singleton
    fun bindAuthorizationFactory(authNavigationScreenFactory: AuthorizationScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindListWalletFactory(profileScreenFactory: ProfileScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindCreateWalletScreenFactory(createWalletScreenFactory: CreateWalletScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindCurrenciesScreenFactory(currenciesScreenFactory: CurrenciesScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindSearchWalletScreenFactory(searchWalletScreen: SearchWalletScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindMainNavHostScreenFactory(mainNavHostScreenFactory: MainNavHostScreenFactory): NavigationHostFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindDetailWalletScreenFactory(detailWalletScreenFactory: DetailWalletScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindCreateTransactionScreenFactory(createTransactionScreenFactory: CreateTransactionScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindCategoriesScreenFactory(categoriesScreenFactory: CategoriesScreenFactory): NavigationScreenFactory
}