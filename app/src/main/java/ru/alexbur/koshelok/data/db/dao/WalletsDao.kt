package ru.alexbur.koshelok.data.db.dao

import androidx.room.*
import ru.alexbur.koshelok.data.db.entity.DetailWalletDb
import ru.alexbur.koshelok.data.db.entity.WalletDb

@Dao
interface WalletsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWallet(wallet: WalletDb)

    @Transaction
    @Query("SELECT * FROM Wallets WHERE id=:walletId")
    suspend fun getDetailWalletDb(walletId: Long): DetailWalletDb?

    @Query("DELETE FROM Wallets WHERE id=:walletId")
    suspend fun deleteWallet(walletId: Long)
}
