package com.arseniy.cryptoid.presentation.common

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

abstract class BasePresenter<V : BaseView> : MvpPresenter<V>(), AnkoLogger  {

    protected val compositeDisposable = CompositeDisposable()

    fun disposeAll() {
        compositeDisposable.clear()
    }

    protected fun handleError(throwable: Throwable) {
        viewState.showError()
        error(throwable.message)
    }
}