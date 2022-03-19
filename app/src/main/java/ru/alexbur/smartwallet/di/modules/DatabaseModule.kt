package ru.alexbur.smartwallet.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.alexbur.smartwallet.data.db.SmartWalletDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context): SmartWalletDatabase {
        return Room.databaseBuilder(
            context,
            SmartWalletDatabase::class.java,
            "SmartWallet.db"
        ).build()
    }
}
