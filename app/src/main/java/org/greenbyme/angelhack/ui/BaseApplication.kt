package org.greenbyme.angelhack.ui

import android.app.Application
import android.util.Log
import android.widget.Toast

class BaseApplication : Application() {
    var userId = -1
    fun throwError(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    fun toastMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

}