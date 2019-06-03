package com.arseniy.cryptoid.domain.details

import com.arseniy.cryptoid.domain.common.ResultWrapper
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsInteractor @Inject constructor(private val repository: DetailsRepository) {

    fun get(coinName: String): Single<ResultWrapper<CoinDetails>> = repository.get(coinName)
}