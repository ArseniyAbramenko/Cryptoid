package com.arseniy.cryptoid.data.history.network

import com.arseniy.cryptoid.data.api.ApiProvider
import com.arseniy.cryptoid.data.history.repository.NetworkHistoricalDataSource
import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.domain.history.HistoricalData
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHistoricalDataSourceImpl @Inject constructor(
    private val apiProvider: ApiProvider
) : NetworkHistoricalDataSource {

    override fun get(coinName: String, currency: Currency): Single<List<HistoricalData>> =
        apiProvider.api()
            .getHistoricalDataResponse(coinName, currency)
            .map { it.data.mapToDomain(coinName, currency) }
}
