package com.arseniy.cryptoid.domain.history

import com.arseniy.cryptoid.domain.currency.Currency

data class HistoricalData(
    val coinName: String,
    val priceCurrency: Currency,
    val timestamp: Long,
    val price: Double
)