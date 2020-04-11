package me.turkergoksu.kefilm.utils

import java.text.SimpleDateFormat

/**
 * Created by turkergoksu on 11-Apr-20, 6:44 PM
 */

object StringUtil {

    fun formatDate(
        dateText: String,
        oldDateFormat: SimpleDateFormat,
        newDateFormat: SimpleDateFormat
    ): String {
        val date = oldDateFormat.parse(dateText)
        return newDateFormat.format(date)
    }
}