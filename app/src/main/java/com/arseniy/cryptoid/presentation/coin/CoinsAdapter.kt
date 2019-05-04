package com.arseniy.cryptoid.presentation.coin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arseniy.cryptoid.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_coin.view.*

class CoinsAdapter(private val onItemClick: (String) -> Unit)
    : RecyclerView.Adapter<CoinsAdapter.CoinViewHolder>() {

    private val coins = mutableListOf<CoinPresentation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder =
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item_coin, parent, false)
            .let { CoinViewHolder(it) }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position], onItemClick)
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    fun updateData(data: List<CoinPresentation>) {
        coins.clear()
        coins.addAll(data)
        notifyDataSetChanged()
    }

    class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(coin: CoinPresentation, onItemClick: (String) -> Unit) {
            with(itemView) {
                tvShortName.text = coin.shortName
                tvFullName.text = coin.fullName
                tvPrice.text = coin.price

                Picasso.with(image.context)
                    .load(coin.imageUrl)
                    .fit()
                    .into(image)

                itemView.setOnClickListener { onItemClick(coin.shortName) }
            }
        }
    }
}