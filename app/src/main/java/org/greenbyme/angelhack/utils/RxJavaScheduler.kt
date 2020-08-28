package org.greenbyme.angelhack.utils

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxJavaScheduler {
    companion object {
        fun runOnIoScheduler(func: () -> Unit): Disposable =
            Completable.fromCallable(func)
                .subscribeOn(Schedulers.io())
                .subscribe()


        fun runOnMainThreadScheduler(func: () -> Unit): Disposable =
            Completable.fromCallable(func)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}