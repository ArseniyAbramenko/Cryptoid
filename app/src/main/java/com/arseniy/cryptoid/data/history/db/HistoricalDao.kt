package com.arseniy.cryptoid.data.history.db

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface HistoricalDao {

    @Query("SELECT * FROM historical_data WHERE coin_name = :coinName AND price_currency = :currency ORDER BY id")
    fun getHistoricalData(coinName: String, currency: String): Single<List<RoomHistoricalData>>

    @Transaction
    fun updateHistoricalData(data: List<RoomHistoricalData>) {
        deleteHistoricalData(data[0].coinName, data[0].priceCurrency)
        insertHistoricalData(data)
    }

    @Query("DELETE FROM historical_data WHERE coin_name = :coinName AND price_currency = :currency")
    fun deleteHistoricalData(coinName: String, currency: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistoricalData(data: List<RoomHistoricalData>)
}