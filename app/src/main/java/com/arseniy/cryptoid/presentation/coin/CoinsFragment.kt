package com.arseniy.cryptoid.presentation.coin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.arseniy.cryptoid.R
import com.arseniy.cryptoid.domain.currency.Currency
import com.arseniy.cryptoid.presentation.App
import com.arseniy.cryptoid.presentation.common.BaseMvpFragment
import com.arseniy.cryptoid.presentation.common.RefreshOwner
import com.arseniy.cryptoid.presentation.common.Refreshable
import com.arseniy.cryptoid.presentation.details.BaseDetailsFragment.Companion.COIN_KEY
import com.arseniy.cryptoid.presentation.details.DetailsActivity
import kotlinx.android.synthetic.main.fragment_coins.*
import kotlinx.android.synthetic.main.view_error.*
import javax.inject.Inject

class CoinsFragment : BaseMvpFragment<CoinsView>(), CoinsView, Refreshable {

    companion object {
        fun newInstance() = CoinsFragment()
    }

    private val coinsAdapter by lazy { CoinsAdapter { presenter.openChart(it) } }
    private val refreshOwner by lazy { context as RefreshOwner }

    @Inject
    lateinit var daggerPresenter: CoinsPresenter

    @InjectPresenter
    override lateinit var presenter: CoinsPresenter

    @ProvidePresenter
    fun providePresenter(): CoinsPresenter = daggerPresenter

    override fun onAttach(context: Context?) {
        App.get().component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coins, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.currencyUSD -> process(Currency.USD)
        R.id.currencyEUR -> process(Currency.EUR)
        R.id.currencyRUB -> process(Currency.RUB)
        else -> super.onOptionsItemSelected(item)
    }

    private fun process(currency: Currency): Boolean {
        presenter.getCoins(currency)
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setUpRecyclerView(context)
        if (savedInstanceState == null) onRefreshData()
    }

    private fun setUpRecyclerView(context: Context?) = with(recyclerView) {
        layoutManager = LinearLayoutManager(context)
        adapter = coinsAdapter
        addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )
        setHasFixedSize(true)
    }

    override fun onRefreshData() {
        presenter.getCoins()
    }

    override fun showCoins(coins: List<CoinPresentation>) {
        errorView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        coinsAdapter.updateData(coins)
    }

    override fun openChart(coinName: String) {
        startActivity(Intent(activity, DetailsActivity::class.java).apply { putExtra(COIN_KEY, coinName) })
    }

    override fun showRefresh() {
        refreshOwner.setRefreshState(true)
    }

    override fun hideRefresh() {
        refreshOwner.setRefreshState(false)
    }

    override fun showError() {
        recyclerView.visibility = View.GONE
        errorView.visibility = View.VISIBLE
    }
}