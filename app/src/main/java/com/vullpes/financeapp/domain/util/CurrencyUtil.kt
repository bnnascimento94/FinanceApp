package com.vullpes.financeapp.domain.util

import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrencyFormat(): String {
    return NumberFormat.getNumberInstance(Locale.getDefault()).format(this)
}