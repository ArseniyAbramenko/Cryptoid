package com.arseniy.cryptoid.presentation.details.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arseniy.cryptoid.R
import com.arseniy.cryptoid.presentation.App
import com.arseniy.cryptoid.presentation.common.Refreshable
import com.arseniy.cryptoid.presentation.details.BaseDetailsFragment
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : BaseDetailsFragment<DetailsView>(),
    DetailsView, Refreshable {

    companion object {
        fun newInstance(args: Bundle) = DetailsFragment().apply { arguments = args }
    }

    @Inject
    lateinit var daggerPresenter: DetailsPresenter

    @InjectPresenter
    override lateinit var presenter: DetailsPresenter

    @ProvidePresenter
    fun providePresenter(): DetailsPresenter = daggerPresenter

    override fun onAttach(context: Context?) {
        App.get().component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState == null) onRefreshData()
    }

    override fun onRefreshData() {
        presenter.getDetails(coinName)
    }

    override fun showDetails(details: DetailsPresentation) {
        with(details) {
            tvDetailsPrice.text = price
            tvDetailsVolume.text = volume24h
            tvDetailsOpen.text = openPrice24h
            tvDetailsHigh.text = highPrice24h
            tvDetailsLow.text = lowPrice24h
        }
    }
}