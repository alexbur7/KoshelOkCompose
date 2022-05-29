package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import ru.alexbur.smartwallet.di.navigation.NavigationHostFactory
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import ru.alexbur.smartwallet.ui.auth.AuthorizationScreenFactory
import ru.alexbur.smartwallet.ui.filter.transactions.FilterTransactionsScreenFactory
import ru.alexbur.smartwallet.ui.filter.wallets.FilterWalletsScreenFactory
import ru.alexbur.smartwallet.ui.main.MainNavHostScreenFactory
import ru.alexbur.smartwallet.ui.profile.ProfileScreenFactory
import ru.alexbur.smartwallet.ui.transactions.categories.categoryoperation.CategoriesScreenFactory
import ru.alexbur.smartwallet.ui.transactions.categories.createcategory.CreateCategoryScreenFactory
import ru.alexbur.smartwallet.ui.transactions.create.CreateTransactionScreenFactory
import ru.alexbur.smartwallet.ui.transactions.edit.EditTransactionScreenFactory
import ru.alexbur.smartwallet.ui.wallet.create.CreateWalletScreenFactory
import ru.alexbur.smartwallet.ui.wallet.create.listcurrency.CurrenciesScreenFactory
import ru.alexbur.smartwallet.ui.wallet.detail.DetailWalletScreenFactory
import ru.alexbur.smartwallet.ui.wallet.edit.EditWalletScreenFactory
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
    fun bindEditWalletScreenFactory(editWalletScreenFactory: EditWalletScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindCurrenciesScreenFactory(currenciesScreenFactory: CurrenciesScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindFilterWalletsScreenFactory(filterWalletsScreenFactory: FilterWalletsScreenFactory): NavigationScreenFactory

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

    @IntoSet
    @Binds
    @Singleton
    fun bindCreateCategoryScreenFactory(createCategoryScreenFactory: CreateCategoryScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindFilterTransactionsScreenFactory(filterTransactionsScreenFactory: FilterTransactionsScreenFactory): NavigationScreenFactory

    @IntoSet
    @Binds
    @Singleton
    fun bindEditTransactionScreenFactory(editTransactionScreenFactory: EditTransactionScreenFactory): NavigationScreenFactory
}