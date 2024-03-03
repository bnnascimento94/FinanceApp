package com.vullpes.common.domain.currency

import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrencyFormat(): String {
    return NumberFormat.getNumberInstance(Locale.getDefault()).format(this)
}