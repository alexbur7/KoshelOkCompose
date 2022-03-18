package ru.alexbur.smartwallet.data.db.dao

import androidx.room.*
import ru.alexbur.smartwallet.data.db.entity.BalanceDb
import ru.alexbur.smartwallet.data.db.entity.ExchangeRatesDb
import ru.alexbur.smartwallet.data.db.entity.MainScreenDataDb
import ru.alexbur.smartwallet.data.db.entity.WalletDb

@Dao
interface MainScreenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWallets(wallets: List<WalletDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBalance(balanceDb: BalanceDb)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeRates(exchangeRatesDb: ExchangeRatesDb)

    @Transaction
    suspend fun insertMainScreenData(
        balanceDb: BalanceDb,
        exchangeRatesDb: ExchangeRatesDb,
        wallets: List<WalletDb>
    ) {
        insertBalance(balanceDb)
        insertExchangeRates(exchangeRatesDb)
        addWallets(wallets)
    }

    @Transaction
    @Query("SELECT * FROM Balance WHERE email= :email")
    suspend fun getMainScreenData(email: String): MainScreenDataDb?
}
