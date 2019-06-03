package com.arseniy.cryptoid.presentation.details.chart

import com.arellomobile.mvp.InjectViewState
import com.arseniy.cryptoid.domain.common.ResultWrapper
import com.arseniy.cryptoid.domain.history.HistoricalData
import com.arseniy.cryptoid.domain.history.HistoricalInteractor
import com.arseniy.cryptoid.presentation.common.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.error
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
                    { handleResult(it) },
                    { error(it.message) }
                )
        )
    }

    private fun handleResult(result: ResultWrapper<List<HistoricalData>>) {
        with(viewState) {
            if (result.throwable != null) showNetworkError()
            showChart(result.data)
        }
    }
}