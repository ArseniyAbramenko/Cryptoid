package com.arseniy.cryptoid.domain.history

import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoricalInteractor @Inject constructor(private val repository: HistoricalRepository) {

    fun get(coinName: String): Single<List<HistoricalData>> = repository.get(coinName)
}