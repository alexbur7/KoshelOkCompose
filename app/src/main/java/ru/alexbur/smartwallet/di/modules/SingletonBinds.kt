package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexbur.smartwallet.data.db.source.DetailWalletSource
import ru.alexbur.smartwallet.data.db.source.DetailWalletSourceImpl
import ru.alexbur.smartwallet.data.db.source.WalletSource
import ru.alexbur.smartwallet.data.db.source.WalletSourceImpl
import ru.alexbur.smartwallet.data.repository.DeleteTransactionRepositoryImpl
import ru.alexbur.smartwallet.data.repository.DetailWalletRepositoryImpl
import ru.alexbur.smartwallet.data.repository.SavingDataManagerImpl
import ru.alexbur.smartwallet.domain.repositories.DeleteTransactionRepository
import ru.alexbur.smartwallet.domain.repositories.DetailWalletRepository
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
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
}