package com.arseniy.cryptoid.domain.history

import io.reactivex.Single

interface HistoricalRepository {

    fun get(coinName: String): Single<List<HistoricalData>>
}