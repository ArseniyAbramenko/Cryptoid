package com.arseniy.cryptoid.presentation.details

import com.arseniy.cryptoid.R
import com.arseniy.cryptoid.presentation.common.BaseMvpFragment
import com.arseniy.cryptoid.presentation.common.BaseView
import com.arseniy.cryptoid.presentation.common.RefreshOwner
import com.arseniy.cryptoid.presentation.common.Refreshable
import org.jetbrains.anko.longToast

abstract class BaseDetailsFragment<V : BaseView> : BaseMvpFragment<V>(), BaseView, Refreshable {

    companion object {
        const val COIN_KEY = "COIN_KEY"
    }

    protected val coinName: String by lazy {
        arguments?.getString(COIN_KEY)
            ?: throw IllegalStateException("Could not get value from arguments by $COIN_KEY")
    }

    private val refreshOwner by lazy { context as RefreshOwner }

    override fun showRefresh() {
        refreshOwner.setRefreshState(true)
    }

    override fun hideRefresh() {
        refreshOwner.setRefreshState(false)
    }

    override fun showNetworkError() {
        activity?.longToast(getString(R.string.error_network))
    }
}