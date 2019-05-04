package com.arseniy.cryptoid.domain.coin

import com.arseniy.cryptoid.domain.currency.Currency
import io.reactivex.Single

interface CoinRepository {

    fun get(currency: Currency? = null): Single<List<Coin>>
}