package com.arseniy.cryptoid.presentation.coin

import com.arellomobile.mvp.InjectViewState
import com.arseniy.cryptoid.domain.coin.Coin
import com.arseniy.cryptoid.domain.coin.CoinInteractor
import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.presentation.common.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    private fun handleResult(coins: List<Coin>) {
        with(viewState) {
            if (coins.isNullOrEmpty()) showError() else showCoins(coins.mapToPresentation())
        }
    }

    fun openChart(coinName: String) {
        viewState.openChart(coinName)
    }
}