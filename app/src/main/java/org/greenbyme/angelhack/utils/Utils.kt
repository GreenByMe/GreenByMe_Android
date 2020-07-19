package org.greenbyme.angelhack.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.util.Log
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
        fun getNumToDay(day: Int): String {
            val list = listOf<String>("일", "월", "화", "수", "목", "금", "토")
            return list[day]
        }

        fun formatTimeMonthDay(time: String): String {
            if (time.isNotBlank()) {
                val sim = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss")
                try {
                    val date = sim.parse(time)
                    val ret = "${date.month + 1}/${date.date + 1}"
                    Log.d("utils", ret)
                    return ret
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            Log.d("utils", "fuck")
            return "funck"
        }

        fun formatTimeMonthDayDate(time: String): String {
            if (time.isNotBlank()) {
                val sim = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss")
                try {
                    val date = sim.parse(time)
                    val ret = "${date.month + 1}/${date.date + 1}/${getNumToDay(date.day)}"
                    Log.d("utils", ret)
                    return ret
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            Log.d("utils", "fuck")
            return "funck"
        }

        fun shareInstagram(context: Context, view: View) {

            view.buildDrawingCache()
            val bitmap = view.drawingCache

            val storage: String =
                Environment.getExternalStorageDirectory().absolutePath
            val fileName = "green.png"
            val folderName = ""
            val fullPath = storage + folderName
            val filePath: File
            val realfile = File(Environment.getExternalStorageDirectory(), fileName)
            try {
                realfile.createNewFile()
                val fos = FileOutputStream(realfile)
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
                realfile
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