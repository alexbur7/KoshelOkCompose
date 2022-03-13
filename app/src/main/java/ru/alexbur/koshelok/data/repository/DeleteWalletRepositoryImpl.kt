package ru.alexbur.koshelok.data.repository

import ru.alexbur.koshelok.data.db.source.WalletSource
import ru.alexbur.koshelok.data.service.AppService
import ru.alexbur.koshelok.domain.repositories.DeleteWalletRepository
import javax.inject.Inject

class DeleteWalletRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val walletSource: WalletSource
) : DeleteWalletRepository {

    override suspend fun deleteWallet(walletId: Long): Result<Boolean> {
        return appService.deleteWallet(walletId).onSuccess {
            walletSource.deleteWallet(walletId)
        }
    }
}
