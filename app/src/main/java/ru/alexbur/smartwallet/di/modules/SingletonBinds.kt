package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexbur.smartwallet.data.db.source.DetailWalletSource
import ru.alexbur.smartwallet.data.db.source.DetailWalletSourceImpl
import ru.alexbur.smartwallet.data.db.source.WalletSource
import ru.alexbur.smartwallet.data.db.source.WalletSourceImpl
import ru.alexbur.smartwallet.data.error_handler.ErrorHandlerImpl
import ru.alexbur.smartwallet.data.repository.*
import ru.alexbur.smartwallet.domain.error_handler.ErrorHandler
import ru.alexbur.smartwallet.domain.repositories.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SingletonBinds {

    @Binds
    @Singleton
    fun bindCurrencyRepository(savingDataManagerImpl: SavingDataManagerImpl): SavingDataManager

    @Binds
    fun bindDetailWalletRepository(detailWalletRepositoryImpl: DetailWalletRepositoryImpl)
            : DetailWalletRepository

    @Binds
    fun bindsWalletSource(walletSourceImpl: WalletSourceImpl): WalletSource

    @Binds
    fun bindsDetailWalletSource(detailWalletSourceImpl: DetailWalletSourceImpl): DetailWalletSource

    @Binds
    fun bindOptionTransactionRepository(optionsTransactionRepositoryImpl: DeleteTransactionRepositoryImpl)
            : DeleteTransactionRepository

    @Binds
    fun bindLoadCategoriesRepository(loadCategoriesRepositoryImpl: LoadCategoriesRepositoryImpl)
            : LoadCategoriesRepository

    @Binds
    fun bindRegistrationRepository(registrationRepositoryImpl: RegistrationRepositoryImpl)
            : RegistrationRepository

    @Binds
    fun bindErrorHandler(errorHandlerImpl: ErrorHandlerImpl): ErrorHandler
}