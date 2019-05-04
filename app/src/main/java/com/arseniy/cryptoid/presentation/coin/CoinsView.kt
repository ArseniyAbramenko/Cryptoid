package com.arseniy.cryptoid.presentation.coin

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.arseniy.cryptoid.presentation.common.BaseView

interface CoinsView : BaseView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showCoins(coins: List<CoinPresentation>)

    @StateStrategyType(SkipStrategy::class)
    fun openChart(coinName: String)
}