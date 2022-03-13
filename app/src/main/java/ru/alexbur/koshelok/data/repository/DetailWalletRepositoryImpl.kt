package ru.alexbur.koshelok.data.repository

import android.content.Context
import ru.alexbur.koshelok.data.db.source.WalletSource
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.alexbur.koshelok.data.db.source.DetailWalletSource
import ru.alexbur.koshelok.data.extentions.checkDate
import ru.alexbur.koshelok.data.extentions.getFormattedDate
import ru.alexbur.koshelok.data.mappers.transactions.TransactionApiToDetailWalletTransactionMapper
import ru.alexbur.koshelok.data.mappers.wallets.WalletApiToHeaderWalletMapper
import ru.alexbur.koshelok.data.service.AppService
import ru.alexbur.koshelok.data.service.api.DetailWalletApi
import ru.alexbur.koshelok.domain.entities.wallet.DetailWalletItem
import ru.alexbur.koshelok.domain.repositories.DetailWalletRepository
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
        return appService.getWallet(walletId).onSuccess {
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
