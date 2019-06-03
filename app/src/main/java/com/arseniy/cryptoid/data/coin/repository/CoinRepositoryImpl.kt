package com.arseniy.cryptoid.data.coin.repository

import com.arseniy.cryptoid.data.currency.SharedPrefsCurrencyDataSource
import com.arseniy.cryptoid.domain.coin.Coin
import com.arseniy.cryptoid.domain.coin.CoinRepository
import com.arseniy.cryptoid.domain.common.ResultWrapper
import com.arseniy.cryptoid.domain.currency.Currency
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoinRepositoryImpl @Inject constructor(
    private val networkCoinDataSource: NetworkCoinDataSource,
    private val dbCoinDataSource: DBCoinDataSource,
    private val sharedPrefsCurrencyDataSource: SharedPrefsCurrencyDataSource
) : CoinRepository {

    override fun get(currency: Currency?): Single<ResultWrapper<List<Coin>>> {
        if (currency != null) sharedPrefsCurrencyDataSource.insert(currency)
        val prefsCurrency = sharedPrefsCurrencyDataSource.get()
        return networkCoinDataSource.get(prefsCurrency)
            .map { ResultWrapper(it) }
            .doOnSuccess { dbCoinDataSource.update(it.data) }
            .onErrorResumeNext { throwable ->
                dbCoinDataSource.get(prefsCurrency).map { ResultWrapper(it, throwable) }
            }
    }
}