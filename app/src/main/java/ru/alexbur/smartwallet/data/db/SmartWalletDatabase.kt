package ru.alexbur.smartwallet.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alexbur.smartwallet.data.db.dao.MainScreenDao
import ru.alexbur.smartwallet.data.db.dao.TransactionDao
import ru.alexbur.smartwallet.data.db.dao.WalletsDao
import ru.alexbur.smartwallet.data.db.entity.BalanceDb
import ru.alexbur.smartwallet.data.db.entity.ExchangeRatesDb
import ru.alexbur.smartwallet.data.db.entity.TransactionDb
import ru.alexbur.smartwallet.data.db.entity.WalletDb

@Database(
    version = 1,
    entities = [TransactionDb::class, WalletDb::class, BalanceDb::class, ExchangeRatesDb::class],
    exportSchema = false
)
abstract class SmartWalletDatabase : RoomDatabase() {

    abstract fun getWalletsDao(): WalletsDao

    abstract fun getTransactionDao(): TransactionDao

    abstract fun getMainScreenDao(): MainScreenDao
}