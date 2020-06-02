package com.example.assignmenttest.util

import java.text.SimpleDateFormat
import java.util.*

fun formatDateTime(givenDate: String, givenFormat: String, requiredFormat: String): String {
    var formattedDate = ""
    if (givenDate.isNotEmpty() && givenDate != "null") {
        val format = SimpleDateFormat(givenFormat, Locale.getDefault())
        val calendar: Calendar = Calendar.getInstance()
        format.timeZone = TimeZone.getTimeZone("UTC")
        calendar.time = format.parse(givenDate)
        val outputFormat = SimpleDateFormat(requiredFormat, Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()
        formattedDate = outputFormat.format(calendar.time)
    }
    return formattedDate
}