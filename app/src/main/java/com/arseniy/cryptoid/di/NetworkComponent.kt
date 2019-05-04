package com.arseniy.cryptoid.di

import com.arseniy.cryptoid.data.api.Api
import com.arseniy.cryptoid.domain.currency.Currency
import dagger.BindsInstance
import dagger.Component

@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun currency(currency: Currency): Builder
        fun build(): NetworkComponent
    }

    fun api(): Api
}