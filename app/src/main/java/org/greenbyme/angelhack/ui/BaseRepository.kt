package org.greenbyme.angelhack.ui

import android.util.Log
import io.reactivex.disposables.CompositeDisposable

open class BaseRepository {
    val disposable = CompositeDisposable()
    fun throwError(throwable: Throwable) {
        throwable.localizedMessage?.let {
            Log.e("repository", it)
        }
    }
}