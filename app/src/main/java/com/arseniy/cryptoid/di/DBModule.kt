package com.arseniy.cryptoid.di

import android.arch.persistence.room.Room
import android.content.Context
import com.arseniy.cryptoid.data.coin.db.CoinDao
import com.arseniy.cryptoid.data.db.CryptoDB
import com.arseniy.cryptoid.data.details.db.DetailsDao
import com.arseniy.cryptoid.data.history.db.HistoricalDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DBModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideDB(context: Context): CryptoDB =
        Room.databaseBuilder(context, CryptoDB::class.java, "crypto_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    @JvmStatic
    fun provideCoinDao(db: CryptoDB): CoinDao = db.getCoinDao()

    @Provides
    @Singleton
    @JvmStatic
    fun provideHistoricalDao(db: CryptoDB): HistoricalDao = db.getHistoricalDao()

    @Provides
    @Singleton
    @JvmStatic
    fun provideDetailsDao(db: CryptoDB): DetailsDao = db.getDetailsDao()
}