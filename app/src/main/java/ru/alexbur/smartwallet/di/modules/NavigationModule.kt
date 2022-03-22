package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
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
@InstallIn(ActivityRetainedComponent::class)
interface NavigationModule {

    @IntoSet
    @Binds
    @ActivityRetainedScoped
    fun bindAuthorizationFactory(authNavigationScreenFactory: AuthorizationScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @ActivityRetainedScoped
    fun bindListWalletFactory(listWalletScreenFactory: ListWalletScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @ActivityRetainedScoped
    fun bindCreateWalletScreenFactory(createWalletScreenFactory: CreateWalletScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @ActivityRetainedScoped
    fun bindCurrenciesScreenFactory(currenciesScreenNavigation: CurrenciesScreenNavigation): NavigationScreenFactory

    @IntoSet
    @Binds
    @ActivityRetainedScoped
    fun bindSearchWalletScreenFactory(searchWalletScreen: SearchWalletScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @ActivityRetainedScoped
    fun bindMainNavHostScreenFactory(mainNavHostScreenFactory: MainNavHostScreenFactory): NavigationHostFactory

    @IntoSet
    @Binds
    @ActivityRetainedScoped
    fun bindDetailWalletScreenFactory(detailWalletScreenFactory: DetailWalletScreenFactory): NavigationScreenFactory
}