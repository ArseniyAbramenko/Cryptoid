package com.arseniy.cryptoid.data.details.repository

import com.arseniy.cryptoid.data.currency.SharedPrefsCurrencyDataSource
import com.arseniy.cryptoid.domain.common.ResultWrapper
import com.arseniy.cryptoid.domain.details.CoinDetails
import com.arseniy.cryptoid.domain.details.DetailsRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsRepositoryImpl @Inject constructor(
    private val networkDetailsDataSource: NetworkDetailsDataSource,
    private val dbDetailsDataSource: DBDetailsDataSource,
    private val sharedPrefsCurrencyDataSource: SharedPrefsCurrencyDataSource
) : DetailsRepository {

    override fun get(coinName: String): Single<ResultWrapper<CoinDetails>> {
        val prefsCurrency = sharedPrefsCurrencyDataSource.get()
        return networkDetailsDataSource.get(coinName, prefsCurrency)
            .map { ResultWrapper(it) }
            .doOnSuccess { dbDetailsDataSource.update(it.data) }
            .onErrorResumeNext { throwable ->
                dbDetailsDataSource.get(coinName, prefsCurrency).map { ResultWrapper(it, throwable) }
            }
    }
}