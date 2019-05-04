package com.arseniy.cryptoid.data.details.db

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface DetailsDao {

    @Query("SELECT * FROM coin_details WHERE coin_name = :coinName AND price_currency = :currency")
    fun getDetails(coinName: String, currency: String): Single<RoomCoinDetails>

    @Transaction
    fun updateDetails(details: RoomCoinDetails) {
        deleteDetails(details.coinName, details.priceCurrency)
        insertDetails(details)
    }

    @Query("DELETE FROM coin_details WHERE coin_name = :coinName AND price_currency = :currency")
    fun deleteDetails(coinName: String, currency: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(details: RoomCoinDetails)
}