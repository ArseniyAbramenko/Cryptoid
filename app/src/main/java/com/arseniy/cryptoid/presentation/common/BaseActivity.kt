package com.arseniy.cryptoid.presentation.common

import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_one_fragment.*

abstract class BaseActivity : AppCompatActivity(),
    SwipeRefreshLayout.OnRefreshListener, RefreshOwner {

    protected fun refreshFragments(vararg ids: Int) {
        for (id in ids) {
            (supportFragmentManager.findFragmentById(id) as Refreshable).onRefreshData()
        }
    }

    protected fun changeFragment(id: Int, fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(id, fragment)
            .commit()
    }

    abstract override fun onRefresh()

    override fun setRefreshState(refreshing: Boolean) {
        refresher.post { refresher.isRefreshing = refreshing }
    }
}