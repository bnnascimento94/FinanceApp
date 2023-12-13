package com.vullpes.financeapp.domain.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

fun Date.tolongStringDate() : String{
    val sdf = SimpleDateFormat("d' de 'MMM' de 'yyyy HH:mm")
    return sdf.format(this)
}

fun Date.toStringDate() : String{
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    return sdf.format(this)
}

fun LocalDate.toDate(): Date?{
    return SimpleDateFormat("yyyy-MM-dd").parse(this.toString());
}