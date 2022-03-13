package ru.alexbur.koshelok.data.mappers

import ru.alexbur.koshelok.data.db.entity.DetailWalletDb
import ru.alexbur.koshelok.data.mappers.transactions.TransactionDbToTransactionApiMapper
import ru.alexbur.koshelok.data.mappers.wallets.WalletDbToWalletApiMapper
import ru.alexbur.koshelok.data.service.api.DetailWalletApi
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
