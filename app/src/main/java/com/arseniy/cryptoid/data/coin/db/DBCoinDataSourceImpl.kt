package com.arseniy.cryptoid.data.coin.db

import com.arseniy.cryptoid.data.coin.repository.DBCoinDataSource
import com.arseniy.cryptoid.domain.coin.Coin
import com.arseniy.cryptoid.domain.currency.Currency
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DBCoinDataSourceImpl @Inject constructor(
    private val dao: CoinDao
) : DBCoinDataSource {

    override fun get(currency: Currency): Single<List<Coin>> =
        dao.getCoins(currency.name).map { it.mapToDomain() }

    override fun update(coins: List<Coin>) {
        dao.updateCoins(coins.mapToData())
    }
}