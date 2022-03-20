package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.alexbur.smartwallet.data.repository.CurrencyRepositoryImpl
import ru.alexbur.smartwallet.domain.repositories.CurrencyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SingletonBinds {

    @Binds
    @Singleton
    fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository
}