package com.arseniy.cryptoid.presentation.coin

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.arseniy.cryptoid.presentation.common.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface CoinsView : BaseView {

    fun showCoins(coins: List<CoinPresentation>)

    fun showNoDataError()

    @StateStrategyType(SkipStrategy::class)
    fun openChart(coinName: String)
}