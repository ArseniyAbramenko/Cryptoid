package com.arseniy.cryptoid.presentation.details

import android.os.Bundle
import com.arseniy.cryptoid.R
import com.arseniy.cryptoid.presentation.common.BaseActivity
import com.arseniy.cryptoid.presentation.details.chart.ChartFragment
import com.arseniy.cryptoid.presentation.details.details.DetailsFragment
import kotlinx.android.synthetic.main.activity_one_fragment.*

class DetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_fragments)
        if (savedInstanceState == null) {
            intent.extras?.let {
                changeFragment(R.id.firstFragmentContainer, ChartFragment.newInstance(it))
                changeFragment(R.id.secondFragmentContainer, DetailsFragment.newInstance(it))
            }
        }
        refresher.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        refreshFragments(R.id.firstFragmentContainer, R.id.secondFragmentContainer)
    }
}
