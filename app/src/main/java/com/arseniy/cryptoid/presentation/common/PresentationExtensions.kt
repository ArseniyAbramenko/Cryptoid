package com.arseniy.cryptoid.presentation.common

fun Double.withSymbol(priceSymbol: String, digitsAfterZeros: Int): String = "$priceSymbol " +
        (if (this >= 1) {
            "%.2f"
        } else {
            "%.${this.listOfDecimals().numberOfDigitsToDisplay(digitsAfterZeros)}f"
        }).format(this)

private fun Double.listOfDecimals(): List<Int> = this
    .toString()
    .substringAfter(".")
    .toCharArray()
    .map(Character::getNumericValue)

private fun List<Int>.numberOfDigitsToDisplay(afterZeros: Int): Int {
    var leadingZeros = 0
    for (i in this) {
        if (i == 0) leadingZeros++ else break
    }
    return leadingZeros + afterZeros
}