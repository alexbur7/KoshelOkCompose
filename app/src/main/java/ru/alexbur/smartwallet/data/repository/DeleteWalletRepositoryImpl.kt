package ru.alexbur.smartwallet.data.repository

import ru.alexbur.smartwallet.data.db.source.WalletSource
import ru.alexbur.smartwallet.data.extentions.resultRequest
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.domain.repositories.DeleteWalletRepository
import javax.inject.Inject

class DeleteWalletRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val walletSource: WalletSource
) : DeleteWalletRepository {

    override suspend fun deleteWallet(walletId: Long): Result<Boolean> {
        return runCatching { appService.deleteWallet(walletId) }.onSuccess {
            walletSource.deleteWallet(walletId)
        }
    }
}
