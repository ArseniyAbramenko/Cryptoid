package com.arseniy.cryptoid.presentation.coin

import com.arellomobile.mvp.InjectViewState
import com.arseniy.cryptoid.domain.coin.Coin
import com.arseniy.cryptoid.domain.coin.CoinInteractor
import com.arseniy.cryptoid.domain.common.ResultWrapper
import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.presentation.common.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.error
import javax.inject.Inject

@InjectViewState
class CoinsPresenter @Inject constructor(
    private val interactor: CoinInteractor
) : BasePresenter<CoinsView>() {

    fun getCoins(currency: Currency? = null) {
        compositeDisposable.add(
            interactor.get(currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showRefresh() }
                .doFinally { viewState.hideRefresh() }
                .subscribe(
                    { handleResult(it) },
                    { handleError(it) }
                )
        )
    }

    private fun handleResult(result: ResultWrapper<List<Coin>>) {
        with(viewState) {
            if (result.throwable != null) showNetworkError()
            if (result.data.isNullOrEmpty()) showNoDataError() else showCoins(result.data.mapToPresentation())
        }
    }

    private fun handleError(throwable: Throwable) {
        viewState.showNoDataError()
        error(throwable.message)
    }

    fun openChart(coinName: String) {
        viewState.openChart(coinName)
    }
}