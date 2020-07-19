package org.greenbyme.angelhack.utils

import android.util.Log
import java.text.SimpleDateFormat

class Utils {
    companion object {
        fun formatTimeMonthDay(time: String): String {
            if (time.isNotBlank()) {
                val sim = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss")
                try {
                    val date = sim.parse(time)
                    val ret ="${date.month + 1}/${date.date + 1}"
                    Log.d("utils",ret)
                    return ret
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            Log.d("utils","fuck")
            return "funck"
        }
    }
}