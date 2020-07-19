package org.greenbyme.angelhack.utils

import java.text.SimpleDateFormat

class Utils {
    companion object {
        fun formatTimeMonthDay(time: String): String {
            if (time.isNotBlank()) {
                val sim = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss")
                try {
                    val date = sim.parse(time)
                    return "${date.month + 1}/${date.date + 1}"
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            return "funck"
        }
    }
}