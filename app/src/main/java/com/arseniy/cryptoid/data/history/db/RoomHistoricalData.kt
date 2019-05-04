package com.arseniy.cryptoid.data.history.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.domain.history.HistoricalData

@Entity(tableName = "historical_data")
data class RoomHistoricalData (
    @ColumnInfo(name = "coin_name") var coinName: String,
    @ColumnInfo(name = "price_currency") var priceCurrency: String,
    var timestamp: Long,
    var price: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

fun List<RoomHistoricalData>.mapToDomain(): List<HistoricalData> =
    map {
        with(it) {
            HistoricalData(
                coinName = coinName,
                priceCurrency = Currency.valueOf(priceCurrency),
                timestamp = timestamp,
                price = price
            )
        }
    }

fun List<HistoricalData>.mapToData(): List<RoomHistoricalData> =
    map {
        with(it) {
            RoomHistoricalData(
                coinName = coinName,
                priceCurrency = priceCurrency.name,
                timestamp = timestamp,
                price = price
            )
        }
    }