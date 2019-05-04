package com.arseniy.cryptoid.presentation.details.details

import com.arellomobile.mvp.InjectViewState
import com.arseniy.cryptoid.domain.details.DetailsInteractor
import com.arseniy.cryptoid.presentation.common.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class DetailsPresenter @Inject constructor(
    private val interactor: DetailsInteractor
) : BasePresenter<DetailsView>() {

    fun getDetails(coinName: String) {
        compositeDisposable.add(
            interactor.get(coinName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewState.showRefresh() }
                .doFinally { viewState.hideRefresh() }
                .subscribe(
                    { viewState.showDetails(it.mapToPresentation()) },
                    { handleError(it) }
                )
        )
    }
}