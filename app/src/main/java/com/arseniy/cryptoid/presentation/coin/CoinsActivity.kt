package com.arseniy.cryptoid.presentation.coin

import android.os.Bundle
import com.arseniy.cryptoid.R
import com.arseniy.cryptoid.presentation.common.BaseActivity
import kotlinx.android.synthetic.main.activity_one_fragment.*

class CoinsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_fragment)
        savedInstanceState ?: changeFragment(R.id.firstFragmentContainer, CoinsFragment.newInstance())
        refresher.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        refreshFragments(R.id.firstFragmentContainer)
    }
}
