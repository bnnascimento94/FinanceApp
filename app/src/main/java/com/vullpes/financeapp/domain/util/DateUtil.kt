package com.vullpes.financeapp.domain.util

import java.text.SimpleDateFormat
import java.util.Date

fun Date.tolongStringDate() : String{
    val sdf = SimpleDateFormat("d' de 'MMM' de 'yyyy HH:mm")
    return sdf.format(this)
}