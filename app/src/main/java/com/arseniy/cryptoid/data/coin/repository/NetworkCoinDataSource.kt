package com.arseniy.cryptoid.data.coin.repository

import com.arseniy.cryptoid.domain.coin.Coin
import com.arseniy.cryptoid.domain.currency.Currency
import io.reactivex.Single

interface NetworkCoinDataSource {

    fun get(currency: Currency): Single<List<Coin>>
}