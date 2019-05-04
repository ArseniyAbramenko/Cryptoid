package com.arseniy.cryptoid.data.api

import com.arseniy.cryptoid.data.coin.network.CoinsResponse
import com.arseniy.cryptoid.data.details.network.DetailsNetworkResponse
import com.arseniy.cryptoid.data.history.network.HistoricalNetworkResponse
import com.arseniy.cryptoid.domain.currency.Currency
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("top/totalvolfull")
    fun getCoinsResponse(
        @Query("tsym") convertTo: Currency,
        @Query("limit") limit: Int = 20
    ): Single<CoinsResponse>

    @GET("histoday")
    fun getHistoricalDataResponse(
        @Query("fsym") convertFrom: String,
        @Query("tsym") convertTo: Currency,
        @Query("limit") limit: Int = 30
    ): Single<HistoricalNetworkResponse>

    @GET("generateAvg")
    fun getDetailsResponse(
        @Query("fsym") convertFrom: String,
        @Query("tsym") convertTo: Currency,
        @Query("e") exchange: String = "CCCAGG"
    ): Single<DetailsNetworkResponse>
}