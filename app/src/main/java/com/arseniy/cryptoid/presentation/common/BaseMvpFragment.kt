package com.arseniy.cryptoid.presentation.common

import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseMvpFragment<V : BaseView> : MvpAppCompatFragment() {

    protected abstract val presenter: BasePresenter<V>

    override fun onDetach() {
        presenter.disposeAll()
        super.onDetach()
    }
}