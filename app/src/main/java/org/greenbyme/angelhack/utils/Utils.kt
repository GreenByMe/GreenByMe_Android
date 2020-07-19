package org.greenbyme.angelhack.utils

import android.util.Log
import java.text.SimpleDateFormat

class Utils {
    companion object {
        fun getNumToDay(day :Int):String{
            val list = listOf<String>("일","월","화","수","목","금","토")
            return list[day]
        }
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
        fun formatTimeMonthDayDate(time: String): String {
            if (time.isNotBlank()) {
                val sim = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss")
                try {
                    val date = sim.parse(time)
                    val ret ="${date.month + 1}/${date.date + 1}/${getNumToDay(date.day)}"
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