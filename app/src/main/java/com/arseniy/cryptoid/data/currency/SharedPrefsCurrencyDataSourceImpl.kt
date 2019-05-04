package com.arseniy.cryptoid.data.currency

import android.content.Context
import android.content.SharedPreferences
import com.arseniy.cryptoid.domain.currency.Currency
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsCurrencyDataSourceImpl @Inject constructor(context: Context) :
    SharedPrefsCurrencyDataSource {

    companion object {
        private const val SHARED_PREFS_KEY = "SHARED_PREFS_KEY"
        private const val CURRENCY_KEY = "CURRENCY_KEY"
    }

    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
    }

    override fun get(): Currency = Currency.valueOf(
        sharedPreferences.getString(CURRENCY_KEY, null) ?: Currency.USD.name
    )

    override fun insert(currency: Currency) {
        sharedPreferences.edit().putString(CURRENCY_KEY, currency.name).apply()
    }
}