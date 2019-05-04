package com.arseniy.cryptoid.presentation.details.chart

import com.arellomobile.mvp.InjectViewState
import com.arseniy.cryptoid.domain.history.HistoricalInteractor
import com.arseniy.cryptoid.presentation.common.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class ChartPresenter @Inject constructor(
    private val interactor: HistoricalInteractor
) : BasePresenter<ChartView>() {

    fun getHistoricalData(coinName: String) {
        compositeDisposable.add(
            interactor.get(coinName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showRefresh() }
                .doFinally { viewState.hideRefresh() }
                .subscribe(
                    { viewState.showChart(it) },
                    { handleError(it) }
                )
        )
    }
}