package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import ru.alexbur.smartwallet.di.navigation.NavigationHostFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.auth.AuthorizationScreenFactory
import ru.alexbur.smartwallet.ui.main.MainNavHostScreenFactory
import ru.alexbur.smartwallet.ui.profile.ListWalletScreenFactory
import ru.alexbur.smartwallet.ui.search.SearchWalletScreenFactory
import ru.alexbur.smartwallet.ui.wallet.createwallet.CreateWalletScreenFactory
import ru.alexbur.smartwallet.ui.wallet.createwallet.listcurrency.CurrenciesScreenNavigation
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
    fun bindListWalletFactory(listWalletScreenFactory: ListWalletScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindCreateWalletScreenFactory(createWalletScreenFactory: CreateWalletScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindCurrenciesScreenFactory(currenciesScreenNavigation: CurrenciesScreenNavigation): NavigationScreenFactory

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
}