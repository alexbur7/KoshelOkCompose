package ru.alexbur.smartwallet.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.alexbur.smartwallet.data.db.source.*

@Module
@InstallIn(ViewModelComponent::class)
interface SourceModule {

    @Binds
    fun bindsDetailWalletSource(detailWalletSourceImpl: DetailWalletSourceImpl): DetailWalletSource

    @Binds
    fun bindsMainWalletSource(mainWalletSourceImpl: MainWalletSourceImpl): MainWalletSource

    @Binds
    fun bindsWalletSource(walletSourceImpl: WalletSourceImpl): WalletSource
}
