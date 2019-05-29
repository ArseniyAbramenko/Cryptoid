package com.arseniy.cryptoid.presentation.common

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class PresentationExtensionsTest {

    @Test
    fun test_doubles_more_than_or_one() =
        doublesMoreThanOrOne().forEach {
            assertEquals(it.key, it.value)
        }

    private fun doublesMoreThanOrOne() = mapOf(
        "$ 1,00" to 1.0.withSymbol("$", 4),
        "$ 1,00" to 1.0049.withSymbol("$", 4),
        "€ 123,12" to 123.123.withSymbol("€", 0),
        "€ 123,12" to 123.123.withSymbol("€", -9),
        "\u20BD 123,13" to 123.12999.withSymbol("\u20BD", 4),
        "random string  123,13" to 123.125.withSymbol("random string ", 5)
    )

    @Test
    fun test_doubles_less_than_one() =
        doublesLessThanOne().forEach {
            assertEquals(it.key, it.value)
        }

    private fun doublesLessThanOne() = mapOf(
        "$ 0,001234" to 0.001234.withSymbol("$", 4),
        "$ 0,0012340" to 0.001234.withSymbol("$", 5),
        "$ 0,001" to 0.001234.withSymbol("$", 0),
        "$ 0,001" to 0.001234.withSymbol("$", -9),
        "\u20BD 0,1300" to 0.12999.withSymbol("\u20BD", 4),
        "random string  0,011201" to 0.01120050.withSymbol("random string ", 5)
    )
}