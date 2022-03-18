package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import ru.alexbur.smartwallet.di.navigation.NavigationScreenFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @IntoSet
    @Binds
    @Singleton
    fun navigationAuthorizationFactory(authNavigationScreenFactory: NavigationScreenFactory): NavigationScreenFactory
}