package com.arseniy.cryptoid.data.coin.network

import com.arseniy.cryptoid.domain.coin.Coin
import com.google.gson.annotations.SerializedName

class CoinsResponse(@SerializedName("Data") val coins: List<Coin>)