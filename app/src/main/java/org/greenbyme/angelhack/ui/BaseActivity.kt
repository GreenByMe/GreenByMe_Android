package org.greenbyme.angelhack.ui

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import io.reactivex.disposables.CompositeDisposable
import org.greenbyme.angelhack.network.ApiService

open class BaseActivity : AppCompatActivity() {
    var tag: String = "TAG"
    var disposable = CompositeDisposable()
    val sharePreferences: SharedPreferences by lazy {
        getSharedPreferences("green", Activity.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.apply {
            title = "상세보기"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    val activityContext: Activity
        get() = this


    fun getToken(): String {
        return sharePreferences.getString("token", "") ?: ""
    }


    fun setToken(token: String) {
        sharePreferences.edit {
            putString("token", token)
            ApiService.token = token
        }
    }

    fun throwError(msg: Throwable) {
        Log.e(tag, msg.message)
    }

    fun toastMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}