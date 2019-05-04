package com.arseniy.cryptoid.data.currency

import com.arseniy.cryptoid.domain.currency.Currency

interface SharedPrefsCurrencyDataSource {

    fun get(): Currency

    fun insert(currency: Currency)
}