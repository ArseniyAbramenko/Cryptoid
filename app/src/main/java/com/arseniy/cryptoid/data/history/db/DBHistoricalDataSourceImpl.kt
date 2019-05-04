package com.arseniy.cryptoid.data.history.db

import com.arseniy.cryptoid.data.history.repository.DBHistoricalDataSource
import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.domain.history.HistoricalData
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DBHistoricalDataSourceImpl @Inject constructor(
    private val dao: HistoricalDao
) : DBHistoricalDataSource {

    override fun get(coinName: String, currency: Currency): Single<List<HistoricalData>> =
        dao.getHistoricalData(coinName, currency.name).map { it.mapToDomain() }

    override fun update(data: List<HistoricalData>) {
        dao.updateHistoricalData(data.mapToData())
    }
}