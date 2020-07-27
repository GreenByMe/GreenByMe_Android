package org.greenbyme.angelhack.extention

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun String.convert(from: String, to: String): String {
    val fromFormat = SimpleDateFormat(from)
    val toFormat = SimpleDateFormat(to)

    return try {
        toFormat.format(fromFormat.parse(this))
    } catch (e: Exception) {
        ""
    }
}