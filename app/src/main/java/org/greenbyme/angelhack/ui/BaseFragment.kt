package org.greenbyme.angelhack.ui

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenbyme.angelhack.network.ApiService

open class BaseFragment : Fragment() {
    private var mAllSubscription = CompositeDisposable()
    val sharePreferences: SharedPreferences? by lazy {
        context?.getSharedPreferences("green", Activity.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.putInt(MainActivity.ARG_STATE, MainActivity.DEFAULT_STATE)
    }

    protected fun addSubscription(disposable: Disposable) {
        if (mAllSubscription.isDisposed) {
            mAllSubscription = CompositeDisposable()
        }

        mAllSubscription.add(disposable)
    }

    private fun disposeSubscription() {
        try {
            mAllSubscription.dispose()
        } catch (ignored: Exception) {
        }
    }

    override fun onDestroy() {
        disposeSubscription()
        super.onDestroy()
    }

    fun setToken(token: String) {
        sharePreferences?.edit {
            putString("token", token)
            ApiService.token = token
        }
    }


    fun throwError(msg: Throwable) {
        Log.e(tag, msg.message)
    }

    fun toastMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}