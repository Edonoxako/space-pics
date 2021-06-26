package com.edonoxako.spacepics.picturedetails

import java.text.SimpleDateFormat
import java.util.*

class DateProvider {

    fun getCurrentDateFormatted(): String {
        val currentDate = Date()
        return SimpleDateFormat(DATE_FORMAT, Locale.US).format(currentDate)
    }

    companion object {
        private const val DATE_FORMAT = "yyyy-MM-dd"
    }
}