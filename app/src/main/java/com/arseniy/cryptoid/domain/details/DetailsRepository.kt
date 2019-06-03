package com.arseniy.cryptoid.domain.details

import com.arseniy.cryptoid.domain.common.ResultWrapper
import io.reactivex.Single

interface DetailsRepository {

    fun get(coinName: String): Single<ResultWrapper<CoinDetails>>
}