package com.arseniy.cryptoid.data.details.network

import com.arseniy.cryptoid.data.api.ApiProvider
import com.arseniy.cryptoid.data.details.repository.NetworkDetailsDataSource
import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.domain.details.CoinDetails
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDetailsDataSourceImpl @Inject constructor(
    private val apiProvider: ApiProvider
)  : NetworkDetailsDataSource {

    override fun get(coinName: String, currency: Currency): Single<CoinDetails> =
            apiProvider.api()
                .getDetailsResponse(coinName, currency)
                .map { it.mapToDomain() }
}