package com.arseniy.cryptoid.data.history.repository

import com.arseniy.cryptoid.data.currency.SharedPrefsCurrencyDataSource
import com.arseniy.cryptoid.domain.common.ResultWrapper
import com.arseniy.cryptoid.domain.history.HistoricalData
import com.arseniy.cryptoid.domain.history.HistoricalRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoricalRepositoryImpl @Inject constructor(
    private val networkHistoricalDataSource: NetworkHistoricalDataSource,
    private val dbHistoricalDataSource: DBHistoricalDataSource,
    private val sharedPrefsCurrencyDataSource: SharedPrefsCurrencyDataSource
) : HistoricalRepository {

    override fun get(coinName: String): Single<ResultWrapper<List<HistoricalData>>> {
        val prefsCurrency = sharedPrefsCurrencyDataSource.get()
        return networkHistoricalDataSource.get(coinName, prefsCurrency)
            .map { ResultWrapper(it) }
            .doOnSuccess { dbHistoricalDataSource.update(it.data) }
            .onErrorResumeNext { throwable ->
                dbHistoricalDataSource.get(coinName, prefsCurrency).map { ResultWrapper(it, throwable) }
            }
    }
}