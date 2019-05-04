package com.arseniy.cryptoid.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.arseniy.cryptoid.data.coin.db.CoinDao
import com.arseniy.cryptoid.data.coin.db.RoomCoin
import com.arseniy.cryptoid.data.details.db.DetailsDao
import com.arseniy.cryptoid.data.details.db.RoomCoinDetails
import com.arseniy.cryptoid.data.history.db.HistoricalDao
import com.arseniy.cryptoid.data.history.db.RoomHistoricalData

@Database(entities = [RoomCoin::class, RoomHistoricalData::class, RoomCoinDetails::class], version = 1)
abstract class CryptoDB : RoomDatabase() {

    abstract fun getCoinDao(): CoinDao

    abstract fun getHistoricalDao(): HistoricalDao

    abstract fun getDetailsDao(): DetailsDao
}