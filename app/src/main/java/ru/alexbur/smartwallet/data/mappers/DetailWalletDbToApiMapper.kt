package ru.alexbur.smartwallet.data.mappers

import ru.alexbur.smartwallet.data.db.entity.DetailWalletDb
import ru.alexbur.smartwallet.data.mappers.transactions.TransactionDbToTransactionApiMapper
import ru.alexbur.smartwallet.data.mappers.wallets.WalletDbToWalletApiMapper
import ru.alexbur.smartwallet.data.service.api.DetailWalletApi
import javax.inject.Inject

class DetailWalletDbToApiMapper @Inject constructor(
    private val mapperWallet: WalletDbToWalletApiMapper,
    private val transactionApiMapper: TransactionDbToTransactionApiMapper
) : (DetailWalletDb) -> DetailWalletApi {
    override fun invoke(detailWallet: DetailWalletDb): DetailWalletApi {
        return DetailWalletApi(
            mapperWallet(detailWallet.wallet),
            detailWallet.transactions.map(transactionApiMapper)
        )
    }
}
