package com.arseniy.cryptoid.data.history.network

import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.domain.history.HistoricalData
import com.google.gson.annotations.SerializedName

data class HistoricalNetworkResponse(
    @SerializedName("Data") val data: List<HistoricalNetworkMapper>
)

data class HistoricalNetworkMapper(
    @SerializedName("time") val timestamp: Long,
    @SerializedName("close") val closePrice: Double
)

fun List<HistoricalNetworkMapper>.mapToDomain(coinName: String, currency: Currency): List<HistoricalData> =
    map {
        HistoricalData(
            coinName = coinName,
            priceCurrency = currency,
            timestamp = it.timestamp,
            price = it.closePrice
        )
    }