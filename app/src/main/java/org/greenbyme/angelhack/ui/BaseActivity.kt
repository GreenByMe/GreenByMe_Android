package org.greenbyme.angelhack.ui

import android.app.Activity
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    var tag: String = "TAG"

    val sharePreferences: SharedPreferences by lazy {
        getSharedPreferences("green", Activity.MODE_PRIVATE)
    }

    fun getToken(): String {
        return sharePreferences.getString("token", "") ?: ""
    }

    fun throwError(msg: Throwable) {
        Log.e(tag, msg.message)
    }

    fun toastMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}