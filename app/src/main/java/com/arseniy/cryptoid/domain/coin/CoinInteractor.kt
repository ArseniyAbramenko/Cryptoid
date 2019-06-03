package com.arseniy.cryptoid.domain.coin

import com.arseniy.cryptoid.domain.common.ResultWrapper
import com.arseniy.cryptoid.domain.currency.Currency
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinInteractor @Inject constructor(private val repository: CoinRepository) {

    fun get(currency: Currency?): Single<ResultWrapper<List<Coin>>> = repository.get(currency)
}