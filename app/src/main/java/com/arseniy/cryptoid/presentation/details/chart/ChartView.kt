package com.arseniy.cryptoid.presentation.details.chart

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.arseniy.cryptoid.domain.history.HistoricalData
import com.arseniy.cryptoid.presentation.common.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface ChartView : BaseView {

    fun showChart(data: List<HistoricalData>)
}