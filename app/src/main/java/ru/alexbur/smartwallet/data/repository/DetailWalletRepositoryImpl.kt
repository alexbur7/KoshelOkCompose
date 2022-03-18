package ru.alexbur.smartwallet.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.alexbur.smartwallet.data.db.source.DetailWalletSource
import ru.alexbur.smartwallet.data.db.source.WalletSource
import ru.alexbur.smartwallet.data.extentions.checkDate
import ru.alexbur.smartwallet.data.extentions.getFormattedDate
import ru.alexbur.smartwallet.data.extentions.resultRequest
import ru.alexbur.smartwallet.data.mappers.transactions.TransactionApiToDetailWalletTransactionMapper
import ru.alexbur.smartwallet.data.mappers.wallets.WalletApiToHeaderWalletMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.data.service.api.DetailWalletApi
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.repositories.DetailWalletRepository
import javax.inject.Inject

class DetailWalletRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val mapperTransaction: TransactionApiToDetailWalletTransactionMapper,
    private val mapWallet: WalletApiToHeaderWalletMapper,
    @ApplicationContext
    private val context: Context,
    private val walletSource: WalletSource,
    private val walletDbSource: DetailWalletSource,
) : DetailWalletRepository {

    override suspend fun getServerDetailWalletData(walletId: Long): Result<List<DetailWalletItem>> {
        return resultRequest { appService.getWallet(walletId) }.onSuccess {
            walletSource.insertWallet(wallet = it.walletApi)
            walletDbSource.insertAllTransactions(it.transactions, walletId)
        }.map { detailWalletApi ->
            mapDetailWallet(detailWalletApi = detailWalletApi)
        }
    }

    override suspend fun getDbDetailWalletData(walletId: Long): Result<List<DetailWalletItem>> {
        return walletDbSource.getDetailWallet(walletId).map { detailWalletApi ->
            mapDetailWallet(detailWalletApi = detailWalletApi)
        }
    }

    private fun mapDetailWallet(detailWalletApi: DetailWalletApi): List<DetailWalletItem> {
        val groupMap = detailWalletApi.transactions.sortedByDescending { api -> api.time }
            .groupBy { transactionApi -> transactionApi.time.getFormattedDate() }
        return mutableListOf<DetailWalletItem>().apply {
            add(mapWallet(detailWalletApi.walletApi))
            groupMap.forEach { (key, transactions) ->
                this.add(
                    DetailWalletItem.Day(
                        transactions.firstOrNull()?.time?.checkDate(context) ?: key
                    )
                )
                this.addAll(transactions.map(mapperTransaction))
            }
        }.toList()
    }
}
