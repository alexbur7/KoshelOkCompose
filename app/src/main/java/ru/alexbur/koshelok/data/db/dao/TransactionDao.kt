package ru.alexbur.koshelok.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.alexbur.koshelok.data.db.entity.TransactionDb

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTransactions(transactions: List<TransactionDb>)

    @Query("SELECT * FROM Transactions WHERE walletId= :id")
    suspend fun getTransactionsByWalletId(id: Long): List<TransactionDb>?

    @Query("DELETE FROM Transactions WHERE id=:transactionId")
    suspend fun deleteTransactions(transactionId: Long)
}
