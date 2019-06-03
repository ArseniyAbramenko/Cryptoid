package com.arseniy.cryptoid.presentation.common

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BaseView : MvpView {

    fun showRefresh()

    fun hideRefresh()

    @StateStrategyType(SkipStrategy::class)
    fun showNetworkError()
}