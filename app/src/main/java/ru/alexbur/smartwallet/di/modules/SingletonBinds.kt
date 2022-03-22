package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexbur.smartwallet.data.repository.SavingDataManagerImpl
import ru.alexbur.smartwallet.domain.repositories.SavingDataManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SingletonBinds {

    @Binds
    @Singleton
    fun bindCurrencyRepository(savingDataManagerImpl: SavingDataManagerImpl): SavingDataManager
}