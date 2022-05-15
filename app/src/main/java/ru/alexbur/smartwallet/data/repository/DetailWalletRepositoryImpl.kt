package ru.alexbur.smartwallet.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.alexbur.smartwallet.data.db.source.DetailWalletSource
import ru.alexbur.smartwallet.data.db.source.WalletSource
import ru.alexbur.smartwallet.data.extentions.checkDate
import ru.alexbur.smartwallet.data.extentions.getFormattedDate
import ru.alexbur.smartwallet.data.extentions.resultRequest
import ru.alexbur.smartwallet.data.mappers.transactions.TransactionApiToDetailWalletTransactionMapper
import ru.alexbur.smartwallet.data.mappers.wallets.WalletApiToWalletEntityMapper
import ru.alexbur.smartwallet.data.service.AppService
import ru.alexbur.smartwallet.data.service.api.TransactionApi
import ru.alexbur.smartwallet.domain.entities.wallet.DetailWalletItem
import ru.alexbur.smartwallet.domain.entities.wallet.WalletEntity
import ru.alexbur.smartwallet.domain.repositories.DetailWalletRepository
import javax.inject.Inject

class DetailWalletRepositoryImpl @Inject constructor(
    private val appService: AppService,
    private val mapperTransaction: TransactionApiToDetailWalletTransactionMapper,
    private val mapWallet: WalletApiToWalletEntityMapper,
    @ApplicationContext
    private val context: Context,
    private val walletSource: WalletSource,
    private val walletDbSource: DetailWalletSource,
) : DetailWalletRepository {

    override suspend fun getServerWalletsData(): Result<List<WalletEntity>> {
        return runCatching { appService.getWallets() }.onSuccess {
            walletSource.insertWallets(wallets = it)
        }.map { wallets ->
            wallets.map(mapWallet)
        }
    }

    override suspend fun getServerTransactionsData(walletId: Long): Result<List<DetailWalletItem>> {
        return runCatching { appService.getTransaction(walletId = walletId) }.map {
            mapDetailWallet(it)
        }
    }

    override suspend fun getDbWalletData(): Result<List<WalletEntity>> {
        return walletSource.getWallets()
    }

    override suspend fun getDbTransactionsData(walletId: Long): Result<List<DetailWalletItem>> {
        return walletDbSource.getAllTransaction(walletId).map {
            mapDetailWallet(it)
        }
    }

    private fun mapDetailWallet(transactions: List<TransactionApi>): List<DetailWalletItem> {
        val groupMap = transactions.sortedByDescending { api -> api.time }
            .groupBy { transactionApi -> transactionApi.time.getFormattedDate() }
        return mutableListOf<DetailWalletItem>().apply {
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
