package org.greenbyme.angelhack.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat


class Utils {
    companion object {
        fun getCategoryString(category: Int?): String {
            when (category) {
                0 -> return "ALL"
                1 -> return "ENERGY"
                2 -> return "DISPOSABLE"
                3 -> return "TRAFFIC"
                4 -> return "WATERWORKS"
                5 -> return "CAMPAIGN"
            }
            return "NONE"
        }

        @JvmStatic
        fun getCategoryStringKOR(category: String?): String {
            when (category) {
                "ENERGY" -> return "에너지"
                "DISPOSABLE" -> return "일회용품"
                "TRAFFIC" -> return "교통"
                "WATERWORKS" -> return "수자원"
                "CAMPAIGN" -> return "캠페인"
            }
            return "전체"
        }

        fun getDateString(day: Int): String {
            when (day) {
                0 -> return "ALL"
                1 -> return "DAY"
                2 -> return "WEEK"
                3 -> return "MONTH"
            }
            return "ALL"
        }

        fun getNumToDay(day: Int): String {
            val list = listOf<String>("일", "월", "화", "수", "목", "금", "토")
            return list[day]
        }

        @JvmStatic
        fun formatTimeMonthDay(time: String): String {
            if (time.isNotBlank()) {
                val sim = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss")
                try {
                    val date = sim.parse(time)
                    val ret = "${date.month + 1}/${date.date + 1}"
                    return ret
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
            return "."
        }

        @JvmStatic
        fun formatTimeMonthDayDate(time: String?): String {
            if (!time.isNullOrBlank()) {
                val sim = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss")
                try {
                    val date = sim.parse(time)
                    val ret = "${date.month + 1}/${date.date + 1}/${getNumToDay(date.day)}"
                    return ret
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return "."
        }

        @JvmStatic
        fun formatTimeYearMonthDay(time: String?): String {
            if (!time.isNullOrBlank()) {
                val sim = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss")
                try {
                    val date = sim.parse(time)
                    val ret = "${date.year+1900} . ${date.month + 1} . ${date.date + 1}"
                    return ret
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return "."
        }

        fun shareInstagram(context: Context, view: View) {
            view.buildDrawingCache()
            val bitmap = view.drawingCache
            val fileName = "green.png"
            val realFile = File(Environment.getExternalStorageDirectory(), fileName)
            try {
                realFile.createNewFile()
                val fos = FileOutputStream(realFile)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                fos.flush()
                fos.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val share = Intent(Intent.ACTION_SEND)
            share.type = "image/*"
            val uri: Uri = FileProvider.getUriForFile(
                context,
                "org.greenbyme.angelhack.fileprovider",
                realFile
            )

            try {
                share.putExtra(Intent.EXTRA_STREAM, uri)
                share.putExtra(Intent.EXTRA_TEXT, "텍스트는 지원하지 않음!")
                share.setPackage("com.instagram.android")
                context.startActivity(share)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "인스타그램이 설치되어 있지 않습니다.", Toast.LENGTH_SHORT).show()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

}