package com.arseniy.cryptoid.domain.details

import com.arseniy.cryptoid.domain.currency.Currency

data class CoinDetails(
    val coinName: String,
    val priceCurrency: Currency,
    val priceSymbol: String,
    val volume24h: Double,
    val openPrice24h: Double,
    val highPrice24h: Double,
    val lowPrice24h: Double,
    val price: Double
)