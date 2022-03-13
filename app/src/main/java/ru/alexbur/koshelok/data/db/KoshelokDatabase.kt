package ru.alexbur.koshelok.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.alexbur.koshelok.data.db.dao.MainScreenDao
import ru.alexbur.koshelok.data.db.dao.TransactionDao
import ru.alexbur.koshelok.data.db.dao.WalletsDao
import ru.alexbur.koshelok.data.db.entity.BalanceDb
import ru.alexbur.koshelok.data.db.entity.ExchangeRatesDb
import ru.alexbur.koshelok.data.db.entity.TransactionDb
import ru.alexbur.koshelok.data.db.entity.WalletDb

@Database(
    version = 1,
    entities = [TransactionDb::class, WalletDb::class, BalanceDb::class, ExchangeRatesDb::class],
    exportSchema = false
)
abstract class KoshelokDatabase : RoomDatabase() {

    abstract fun getWalletsDao(): WalletsDao

    abstract fun getTransactionDao(): TransactionDao

    abstract fun getMainScreenDao(): MainScreenDao
}