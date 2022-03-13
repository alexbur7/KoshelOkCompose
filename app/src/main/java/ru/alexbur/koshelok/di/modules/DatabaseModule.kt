package ru.alexbur.koshelok.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.alexbur.koshelok.data.db.KoshelokDatabase
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): KoshelokDatabase {
        return Room.databaseBuilder(
            context,
            KoshelokDatabase::class.java,
            "koshelok.db"
        ).build()
    }
}
