package com.arseniy.cryptoid.di

import com.arseniy.cryptoid.data.coin.db.DBCoinDataSourceImpl
import com.arseniy.cryptoid.data.coin.network.NetworkCoinDataSourceImpl
import com.arseniy.cryptoid.data.coin.repository.CoinRepositoryImpl
import com.arseniy.cryptoid.data.coin.repository.DBCoinDataSource
import com.arseniy.cryptoid.data.coin.repository.NetworkCoinDataSource
import com.arseniy.cryptoid.data.currency.SharedPrefsCurrencyDataSource
import com.arseniy.cryptoid.data.currency.SharedPrefsCurrencyDataSourceImpl
import com.arseniy.cryptoid.data.details.db.DBDetailsDataSourceImpl
import com.arseniy.cryptoid.data.details.network.NetworkDetailsDataSourceImpl
import com.arseniy.cryptoid.data.details.repository.DBDetailsDataSource
import com.arseniy.cryptoid.data.details.repository.DetailsRepositoryImpl
import com.arseniy.cryptoid.data.details.repository.NetworkDetailsDataSource
import com.arseniy.cryptoid.data.history.db.DBHistoricalDataSourceImpl
import com.arseniy.cryptoid.data.history.network.NetworkHistoricalDataSourceImpl
import com.arseniy.cryptoid.data.history.repository.DBHistoricalDataSource
import com.arseniy.cryptoid.data.history.repository.HistoricalRepositoryImpl
import com.arseniy.cryptoid.data.history.repository.NetworkHistoricalDataSource
import com.arseniy.cryptoid.domain.coin.CoinRepository
import com.arseniy.cryptoid.domain.details.DetailsRepository
import com.arseniy.cryptoid.domain.history.HistoricalRepository
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
interface AppModule {

    @Binds
    fun sharedPrefsCurrencyDataSource(dataSource: SharedPrefsCurrencyDataSourceImpl): SharedPrefsCurrencyDataSource

    @Binds
    fun coinRepository(repository: CoinRepositoryImpl): CoinRepository

    @Binds
    fun networkCoinDataSource(dataSource: NetworkCoinDataSourceImpl): NetworkCoinDataSource

    @Binds
    fun dbCoinDataSource(dataSource: DBCoinDataSourceImpl): DBCoinDataSource

    @Binds
    fun historicalRepository(repository: HistoricalRepositoryImpl): HistoricalRepository

    @Binds
    fun networkHistoricalDataSource(dataSource: NetworkHistoricalDataSourceImpl): NetworkHistoricalDataSource

    @Binds
    fun dbHistoricalDataSource(dataSource: DBHistoricalDataSourceImpl): DBHistoricalDataSource

    @Binds
    fun detailsRepository(repository: DetailsRepositoryImpl): DetailsRepository

    @Binds
    fun networkDetailsDataSource(dataSource: NetworkDetailsDataSourceImpl): NetworkDetailsDataSource

    @Binds
    fun dbDetailsDataSource(dataSource: DBDetailsDataSourceImpl): DBDetailsDataSource
}
