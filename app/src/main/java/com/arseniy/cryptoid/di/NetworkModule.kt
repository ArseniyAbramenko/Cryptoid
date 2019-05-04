package com.arseniy.cryptoid.di

import com.arseniy.cryptoid.BuildConfig
import com.arseniy.cryptoid.data.api.Api
import com.arseniy.cryptoid.data.coin.network.CoinDeserializer
import com.arseniy.cryptoid.domain.coin.Coin
import com.arseniy.cryptoid.domain.currency.Currency
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    fun provideApi(gson: Gson): Api =
        Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

    @Provides
    @JvmStatic
    fun provideGson(currency: Currency): Gson =
        GsonBuilder()
        .registerTypeAdapter(Coin::class.java, CoinDeserializer(currency))
        .create()
}
