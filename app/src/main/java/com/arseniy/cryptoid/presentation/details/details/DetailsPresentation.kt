package com.arseniy.cryptoid.presentation.details.details

import com.arseniy.cryptoid.domain.details.CoinDetails
import com.arseniy.cryptoid.presentation.common.withSymbol

data class DetailsPresentation(
    val price: String,
    val volume24h: String,
    val openPrice24h: String,
    val highPrice24h: String,
    val lowPrice24h: String
)

fun CoinDetails.mapToPresentation(): DetailsPresentation {
    with(this) {
        priceSymbol.let {
            return DetailsPresentation(
                price.withSymbol(it),
                volume24h.withSymbol(it),
                openPrice24h.withSymbol(it),
                highPrice24h.withSymbol(it),
                lowPrice24h.withSymbol(it)
            )
        }
    }
}

private const val DIGITS_AFTER_ZEROS = 4

private fun Double.withSymbol(priceSymbol: String): String =
    this.withSymbol(priceSymbol, DIGITS_AFTER_ZEROS)
