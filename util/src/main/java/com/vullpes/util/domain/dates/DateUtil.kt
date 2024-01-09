package com.vullpes.util.domain.dates

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
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

fun Date.toLocalDate(): LocalDate{
    return this.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
}