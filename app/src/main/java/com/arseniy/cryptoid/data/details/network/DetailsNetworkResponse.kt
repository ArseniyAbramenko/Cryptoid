package com.arseniy.cryptoid.data.details.network

import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.domain.details.CoinDetails
import com.google.gson.annotations.SerializedName

data class DetailsNetworkResponse(
    @SerializedName("RAW") val raw: Raw,
    @SerializedName("DISPLAY") val display: Display
)

data class Raw(
    @SerializedName("FROMSYMBOL") val coinName: String,
    @SerializedName("TOSYMBOL") val priceCurrency: String,
    @SerializedName("VOLUME24HOURTO") val volume24h: Double,
    @SerializedName("OPEN24HOUR") val openPrice24h: Double,
    @SerializedName("HIGH24HOUR") val highPrice24h: Double,
    @SerializedName("LOW24HOUR") val lowPrice24h: Double,
    @SerializedName("PRICE") val price: Double
)

data class Display(
    @SerializedName("TOSYMBOL") val priceSymbol: String
)

fun DetailsNetworkResponse.mapToDomain(): CoinDetails {
    with(this) {
        return CoinDetails(
            coinName = raw.coinName,
            priceCurrency = Currency.valueOf(raw.priceCurrency),
            priceSymbol = display.priceSymbol,
            volume24h = raw.volume24h,
            openPrice24h = raw.openPrice24h,
            highPrice24h = raw.highPrice24h,
            lowPrice24h = raw.lowPrice24h,
            price = raw.price
        )
    }
}
