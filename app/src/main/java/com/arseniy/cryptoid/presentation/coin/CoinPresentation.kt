package com.arseniy.cryptoid.presentation.coin

import com.arseniy.cryptoid.domain.coin.Coin
import com.arseniy.cryptoid.presentation.common.withSymbol

data class CoinPresentation(
    val shortName: String,
    val fullName: String,
    val imageUrl: String,
    val price: String
)

private const val DIGITS_AFTER_ZEROS = 4

fun List<Coin>.mapToPresentation(): List<CoinPresentation> =
    map {
        with(it) {
            CoinPresentation(
                shortName,
                fullName,
                imageUrl,
                price.withSymbol(priceSymbol, DIGITS_AFTER_ZEROS)
            )
        }
    }
