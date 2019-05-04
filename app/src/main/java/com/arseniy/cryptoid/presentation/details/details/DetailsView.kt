package com.arseniy.cryptoid.presentation.details.details

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.arseniy.cryptoid.presentation.common.BaseView

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailsView : BaseView {

    fun showDetails(details: DetailsPresentation)
}