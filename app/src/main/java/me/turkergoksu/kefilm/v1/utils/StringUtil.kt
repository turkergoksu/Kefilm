package me.turkergoksu.kefilm.v1.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

/**
 * Created by turkergoksu on 11-Apr-20, 6:44 PM
 */

object StringUtil {

    @SuppressLint("SimpleDateFormat")
    fun formatDate(
        dateText: String,
        oldDateFormat: String,
        newDateFormat: String
    ): String {
        val date = SimpleDateFormat(oldDateFormat).parse(dateText)
        return SimpleDateFormat(newDateFormat).format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun getYearFromDate(dateText: String, dateFormat: String): String {
        val date = SimpleDateFormat(dateFormat).parse(dateText)
        return SimpleDateFormat("yyyy").format(date)
    }
}