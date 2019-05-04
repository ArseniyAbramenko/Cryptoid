package com.arseniy.cryptoid.data.details.repository

import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.domain.details.CoinDetails
import io.reactivex.Single

interface NetworkDetailsDataSource {

    fun get(coinName: String, currency: Currency): Single<CoinDetails>
}