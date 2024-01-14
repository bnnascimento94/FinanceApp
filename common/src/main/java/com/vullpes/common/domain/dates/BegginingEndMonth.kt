package com.vullpes.common.domain.dates

import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

object BegginingEndMonth {

    fun execute():Map<String, Date>{
        val gc: Calendar = GregorianCalendar()
        gc.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH))
        gc[Calendar.DAY_OF_MONTH] = 1
        var monthStart: Date = gc.time
        gc.add(Calendar.MONTH, 1)
        gc.add(Calendar.DAY_OF_MONTH, -1)
        var monthEnd: Date = gc.time
        return mapOf("firstDate" to monthStart, "lastDate" to monthEnd)

    }
}