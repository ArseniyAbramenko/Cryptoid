package com.arseniy.cryptoid.data.coin.db

import android.arch.persistence.room.*
import io.reactivex.Single

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin WHERE price_currency = :currency ORDER BY id")
    fun getCoins(currency: String): Single<List<RoomCoin>>

    @Transaction
    fun updateCoins(coins: List<RoomCoin>) {
        deleteCoins(coins[0].priceCurrency)
        insertCoins(coins)
    }

    @Query("DELETE FROM coin WHERE price_currency = :currency")
    fun deleteCoins(currency: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoins(coins: List<RoomCoin>)
}