package com.arseniy.cryptoid.data.coin.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.arseniy.cryptoid.domain.coin.Coin
import com.arseniy.cryptoid.domain.currency.Currency

@Entity(tableName = "coin")
data class RoomCoin (
    @ColumnInfo(name = "short_name") var shortName: String,
    @ColumnInfo(name = "full_name") var fullName: String,
    @ColumnInfo(name = "image_url") var imageUrl: String,
    @ColumnInfo(name = "price_symbol") var priceSymbol: String,
    @ColumnInfo(name = "price_currency") var priceCurrency: String,
    var price: Double
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

fun List<RoomCoin>.mapToDomain(): List<Coin> =
    map {
        with(it) {
            Coin(
                shortName = shortName,
                fullName = fullName,
                imageUrl = imageUrl,
                price = price,
                priceCurrency = Currency.valueOf(priceCurrency),
                priceSymbol = priceSymbol
            )
        }
    }

fun List<Coin>.mapToData(): List<RoomCoin> =
    map {
        with(it) {
            RoomCoin(
                shortName = shortName,
                fullName = fullName,
                imageUrl = imageUrl,
                price = price,
                priceCurrency = priceCurrency.name,
                priceSymbol = priceSymbol
            )
        }
    }