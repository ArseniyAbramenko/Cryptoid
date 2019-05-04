package com.arseniy.cryptoid.data.details.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.domain.details.CoinDetails

@Entity(tableName = "coin_details")
data class RoomCoinDetails (
    @ColumnInfo(name = "coin_name") var coinName: String,
    @ColumnInfo(name = "price_currency") var priceCurrency: String,
    @ColumnInfo(name = "price_symbol") var priceSymbol: String,
    @ColumnInfo(name = "volume_24h") var volume24h: Double,
    @ColumnInfo(name = "open_price_24h") var openPrice24h: Double,
    @ColumnInfo(name = "high_price_24h") var highPrice24h: Double,
    @ColumnInfo(name = "low_price_24h") var lowPrice24h: Double,
    var price: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

fun RoomCoinDetails.mapToDomain(): CoinDetails {
    with(this) {
        return CoinDetails(
            coinName = coinName,
            priceCurrency = Currency.valueOf(priceCurrency),
            priceSymbol = priceSymbol,
            volume24h = volume24h,
            openPrice24h = openPrice24h,
            highPrice24h = highPrice24h,
            lowPrice24h = lowPrice24h,
            price = price
        )
    }
}

fun CoinDetails.mapToData(): RoomCoinDetails {
    with(this) {
        return RoomCoinDetails(
            coinName = coinName,
            priceCurrency = priceCurrency.name,
            priceSymbol = priceSymbol,
            volume24h = volume24h,
            openPrice24h = openPrice24h,
            highPrice24h = highPrice24h,
            lowPrice24h = lowPrice24h,
            price = price
        )
    }
}