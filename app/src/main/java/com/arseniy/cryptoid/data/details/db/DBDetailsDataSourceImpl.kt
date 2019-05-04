package com.arseniy.cryptoid.data.details.db

import com.arseniy.cryptoid.data.details.repository.DBDetailsDataSource
import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.domain.details.CoinDetails
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DBDetailsDataSourceImpl @Inject constructor(
    private val dao: DetailsDao
) : DBDetailsDataSource {

    override fun get(coinName: String, currency: Currency): Single<CoinDetails> =
        dao.getDetails(coinName, currency.name).map { it.mapToDomain() }

    override fun update(details: CoinDetails) {
        dao.updateDetails(details.mapToData())
    }
}