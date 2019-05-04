package com.arseniy.cryptoid.data.coin.network

import com.arseniy.cryptoid.domain.coin.Coin
import com.arseniy.cryptoid.domain.currency.Currency
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CoinDeserializer(private val currency: Currency) : JsonDeserializer<Coin> {

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Coin {
        val jsonObject = json.asJsonObject

        val coinInfo = jsonObject.get("CoinInfo").asJsonObject
        val raw = jsonObject.get("RAW").asJsonObject.get(currency.name).asJsonObject
        val display = jsonObject.get("DISPLAY").asJsonObject.get(currency.name).asJsonObject

        return Coin(
            shortName = coinInfo.get("Name").asString,
            fullName = coinInfo.get("FullName").asString,
            imageUrl = "https://www.cryptocompare.com${coinInfo.get("ImageUrl").asString}",
            price = raw.get("PRICE").asDouble,
            priceCurrency = Currency.valueOf(raw.get("TOSYMBOL").asString),
            priceSymbol = display.get("TOSYMBOL").asString
        )
    }
}
