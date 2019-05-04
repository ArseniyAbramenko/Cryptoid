package com.arseniy.cryptoid.data.coin.network

import com.arseniy.cryptoid.data.api.ApiProvider
import com.arseniy.cryptoid.data.coin.repository.NetworkCoinDataSource
import com.arseniy.cryptoid.domain.coin.Coin
import com.arseniy.cryptoid.domain.currency.Currency
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkCoinDataSourceImpl @Inject constructor(
    private val apiProvider: ApiProvider
) : NetworkCoinDataSource {

    override fun get(currency: Currency): Single<List<Coin>> =
        apiProvider.api(currency).getCoinsResponse(currency).map { it.coins }
}