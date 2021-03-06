package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.alexbur.smartwallet.data.repository.*
import ru.alexbur.smartwallet.domain.repositories.*

@Module
@InstallIn(ViewModelComponent::class)
interface BindsRepositoryModule {

    @Binds
    fun bindLoadCurrencyRepository(currenciesRepositoryImpl: LoadCurrenciesRepositoryImpl): LoadCurrenciesRepository

    @Binds
    fun bindListWalletRepository(listWalletRepositoryImpl: MainScreenRepositoryImpl)
            : MainScreenRepository

    @Binds
    fun bindActionTransactionRepository(actionTransactionRepositoryImpl: CreateTransactionRepositoryImpl)
            : CreateTransactionRepository

    @Binds
    fun bindCreateWalletRepository(createWalletRepositoryImpl: CreateWalletRepositoryImpl)
            : CreateWalletRepository

    @Binds
    fun bindCreateCategoryRepository(createCategoryRepositoryImpl: CreateCategoryRepositoryImpl)
            : CreateCategoryRepository

    @Binds
    fun bindDeleteWalletRepository(deleteWalletRepositoryImpl: DeleteWalletRepositoryImpl)
            : DeleteWalletRepository
}
