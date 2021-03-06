package ru.alexbur.smartwallet.data.db.dao

import androidx.room.*
import ru.alexbur.smartwallet.data.db.entity.DetailWalletDb
import ru.alexbur.smartwallet.data.db.entity.WalletDb

@Dao
interface WalletsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWallet(wallet: WalletDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWallets(wallets: List<WalletDb>)

    @Transaction
    @Query("SELECT * FROM Wallets WHERE id=:walletId")
    suspend fun getDetailWalletDb(walletId: Long): DetailWalletDb?

    @Query("DELETE FROM Wallets WHERE id=:walletId")
    suspend fun deleteWallet(walletId: Long)

    @Query("SELECT * FROM Wallets WHERE email=:email")
    suspend fun getWallets(email: String): List<WalletDb>
}
