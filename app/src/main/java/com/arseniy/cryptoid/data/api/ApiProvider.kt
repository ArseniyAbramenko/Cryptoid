package com.arseniy.cryptoid.data.api

import com.arseniy.cryptoid.di.DaggerNetworkComponent
import com.arseniy.cryptoid.domain.currency.Currency
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiProvider @Inject constructor() {

    private var currency = Currency.USD
    private var api = buildApi(currency)

    fun api(currency: Currency = this.currency): Api {
        if (currency != this.currency) {
            api = buildApi(currency)
            this.currency = currency
        }
        return api
    }

    private fun buildApi(currency: Currency): Api =
        DaggerNetworkComponent.builder()
            .currency(currency)
            .build()
            .api()
}