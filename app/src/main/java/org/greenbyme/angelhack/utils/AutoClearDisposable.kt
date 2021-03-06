package org.greenbyme.angelhack.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.greenbyme.angelhack.ui.BaseActivity

/*
    @params : lifecycleOwner
    @params :alwaysClearOnStop if view disposable false, or true
    @params :compositeDisposable
 */
class AutoClearDisposable(
    private val lifecycleOwner: BaseActivity,
    private val alwaysClearOnStop: Boolean = false,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : LifecycleObserver {

    fun add(disposable: Disposable) {
        check(lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED))
        compositeDisposable.add(disposable)
    }

    fun addAll(vararg disposable: Disposable) {
        check(lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED))
        compositeDisposable.addAll(*disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun cleanUp() {
        if (alwaysClearOnStop) {
            compositeDisposable.clear()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachSelf() {
        compositeDisposable.clear()
        lifecycleOwner.lifecycle.removeObserver(this)
    }

    operator fun plusAssign(subscribe: Disposable) {
        add(subscribe)
    }
}