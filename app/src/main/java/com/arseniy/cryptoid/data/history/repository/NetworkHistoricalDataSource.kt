package com.arseniy.cryptoid.data.history.repository

import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.domain.history.HistoricalData
import io.reactivex.Single

interface NetworkHistoricalDataSource {

    fun get(coinName: String, currency: Currency): Single<List<HistoricalData>>
}