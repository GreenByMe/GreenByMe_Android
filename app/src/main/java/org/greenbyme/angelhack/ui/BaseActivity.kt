package org.greenbyme.angelhack.ui

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity(){
    var tag : String = "TAG"
    fun throwError(msg: Throwable) {
        Log.e(tag, msg.message)
    }

    fun toastMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}