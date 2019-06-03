package com.arseniy.cryptoid.domain.history

import com.arseniy.cryptoid.domain.common.ResultWrapper
import io.reactivex.Single

interface HistoricalRepository {

    fun get(coinName: String): Single<ResultWrapper<List<HistoricalData>>>
}