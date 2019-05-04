package com.arseniy.cryptoid.presentation.details.chart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arseniy.cryptoid.R
import com.arseniy.cryptoid.domain.history.HistoricalData
import com.arseniy.cryptoid.presentation.App
import com.arseniy.cryptoid.presentation.common.Refreshable
import com.arseniy.cryptoid.presentation.details.BaseDetailsFragment
import kotlinx.android.synthetic.main.fragment_chart.*
import javax.inject.Inject

class ChartFragment : BaseDetailsFragment<ChartView>(),
    ChartView, Refreshable {

    companion object {
        fun newInstance(args: Bundle) = ChartFragment().apply { arguments = args }
    }

    @Inject
    lateinit var daggerPresenter: ChartPresenter

    @InjectPresenter
    override lateinit var presenter: ChartPresenter

    @ProvidePresenter
    fun providePresenter(): ChartPresenter = daggerPresenter

    override fun onAttach(context: Context?) {
        App.get().component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.title = coinName
        if (savedInstanceState == null) onRefreshData()
    }

    override fun onRefreshData() {
        presenter.getHistoricalData(coinName)
    }

    override fun showChart(data: List<HistoricalData>) {
        chart.setUp(data)
    }
}