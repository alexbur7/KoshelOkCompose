package ru.alexbur.koshelok.domain.repositories

import ru.alexbur.koshelok.domain.entities.wallet.DetailWalletItem

interface DetailWalletRepository {

    suspend fun getServerDetailWalletData(walletId: Long): Result<List<DetailWalletItem>>

    suspend fun getDbDetailWalletData(walletId: Long): Result<List<DetailWalletItem>>
}