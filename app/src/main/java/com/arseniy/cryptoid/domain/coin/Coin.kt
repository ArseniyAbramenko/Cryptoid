package com.arseniy.cryptoid.domain.coin

import com.arseniy.cryptoid.domain.currency.Currency

data class Coin(
    val shortName: String,
    val fullName: String,
    val imageUrl: String,
    val price: Double,
    val priceCurrency: Currency,
    val priceSymbol: String
)