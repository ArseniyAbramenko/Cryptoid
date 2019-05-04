package com.arseniy.cryptoid.domain.details

import io.reactivex.Single

interface DetailsRepository {

    fun get(coinName: String): Single<CoinDetails>
}