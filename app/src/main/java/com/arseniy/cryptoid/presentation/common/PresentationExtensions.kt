package com.arseniy.cryptoid.presentation.common

// Returns the string presentation of double with price symbol defined by first parameter.
// If double is less than 1, the number of decimals depends on second parameter.
fun Double.withSymbol(priceSymbol: String, digitsAfterZeros: Int): String {
    val digits = if (digitsAfterZeros >= 1) digitsAfterZeros else 1
    val formatter = if (this >= 1) "%.2f" else "%.${this.listOfDecimals().numberOfDigitsToDisplay(digits)}f"
    return "$priceSymbol ${formatter.format(this)}"
}

// Returns the integer list of decimals for double.
private fun Double.listOfDecimals(): List<Int> = this
    .toString()
    .substringAfter(".")
    .toCharArray()
    .map(Character::getNumericValue)

// Returns the number of decimals to display for double.
private fun List<Int>.numberOfDigitsToDisplay(afterZeros: Int): Int {
    var leadingZeros = 0
    for (n in this) {
        if (n == 0) leadingZeros++ else break
    }
    return leadingZeros + afterZeros
}