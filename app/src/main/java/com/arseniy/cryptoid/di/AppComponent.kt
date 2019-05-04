package com.arseniy.cryptoid.di

import android.content.Context
import com.arseniy.cryptoid.presentation.coin.CoinsFragment
import com.arseniy.cryptoid.presentation.details.chart.ChartFragment
import com.arseniy.cryptoid.presentation.details.details.DetailsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DBModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(fragment: CoinsFragment)

    fun inject(fragment: ChartFragment)

    fun inject(fragment: DetailsFragment)
}